(ns scandent.language.nederlands
  (:require [net.cgrand.enlive-html :as html]))

(def ^:dynamic *base-url* "http://www.fourlangwebprogram.com/fourlang/nl/ww_nl_A.htm")

(def ^:dynamic *url-sterke-werkwoorden* "http://www.dutchgrammar.com/nl/?n=Verbs.ir03")


;; ;;   Enlive                                       CSS
;;   =======================================================
;;   [:div]                                       div
;;   [:body :script]                              body script
;;   #{[:ul.outline :> :li] [:ol.outline :> li]}  ul.outline > li, ol.outline > li
;;   [#{:ul.outline :ol.outline} :> :li]          ul.outline > li, ol.outline > li
;;   [[#{:ul :ol} :.outline] :> :li]              ul.outline > li, ol.outline > li
;;   [:div :> :*]                                 div > *
;;   [:div :> text-node]                          (text children of a div)
;;   [:div :> any-node]                           (all children (including text nodes and comments) of a div)
;;   {[:dt] [:dl]}                                (fragments starting by DT and ending at the *next* DD)

;; verleden
;; onvoltooid o.v.t.
;; voltooid v.v.t.

;; tegenwoordig
;; onvoltooid o.t.t.
;; voltooid v.t.t

;; toekomend
;; onvoltooid tegenwoordig/verleden toekomende tijd o.t.t.t. ; o.v.t.t.
;; voltooid tegenwoordig/verleden toekomende tijd v.t.t.t. ; v.v.t.t.

(def tijd {:verleden :tegenwoordig :toekomend})


;; dynamic fetch
(defn fetch-url [url]
  (html/html-resource (java.net.URL. url)))


;; selectors

;wikitext
(defn nl-sterk [] (map html/text (html/select (fetch-url *url-sterke-werkwoorden*) [:table.gray :td])))

(comment "TODO Achter de voltooid deelwoorden waarvoor we het hulpwerkwoord zijn gebruiken, staat steeds een asterisk (*).")

(defn print2
"Infinitief, verleden tijd enkelvoud,	verleden tijd meervoud,	voltooid deelwoord, vervoegingspatroon"
  [] (doseq [line
             (map (fn [[inf vte vtm vdw vp]]
                         (assoc {} :inf inf :vte vte :vtm vtm :vdw vdw :vp vp))
                             (partition 5 (nl-sterk)))]
        (println line)))

;(print2)


(defn hn-headlines []
  (map html/text (html/select (fetch-url *base-url*) [:thead :tr :th])))

(defn hn-points []
  (map html/text (html/select (fetch-url *base-url*) [:tbody :tr])))

(defn print-headlines-and-points []
  (doseq [line (map #(str %1 " (" %2 ")") (hn-headlines) (hn-points))]
    (println line)))



;(print-headlines-and-points)

; Eigenlijk hebben we maar twee tijden: de tegenwoordige tijd en de verleden tijd.
; Er zijn echter ook een aantal 'semi-tijden' die ontstaat door interactie tussen tijd
; (tegenwoordig of verleden), aspect (gebeurtenis of toestand) en stemming (zeker of hypothetisch).

; Dit resulteert in een lijst van acht 'basistijden'.

;; Dit resulteert in een lijst van acht 'basistijden'.
;; 1. onvoltooid tegenwoordige tijd (ott)	ik werk
;; 2. onvoltooid verleden tijd (ovt)	ik werkte
;; 3. voltooid tegenwoordige tijd (vtt)	ik heb gewerkt
;; 4. voltooid verleden tijd (vvt)	ik had gewerkt
;; 5. onvoltooid tegenwoordige toekomende tijd (ottt)	ik zal werken
;; 6. onvoltooid verleden toekomende tijd (ovtt)	ik zou werken
;; 7. voltooid tegenwoordige toekomende tijd (vttt)	ik zal hebben gewerkt
;; 8. voltooid verleden toekomende tijd (vvtt)	ik zou hebben gewerkt

;; Een andere veel voorkomende 'tijd', die hier niet genoemd is, is de progressieve vorm: ik ben aan het lopen.
;; Deze vorm zal worden besproken in het volgende hoofdstuk onder aan het continuous en te continuous.

;; De persoonlijke voornaamwoorden zijn:
;; 1ste enkelvoud	ik
;; 2de enkelvoud	je/u
;; 3de enkelvoud	hij/ze/het
;; 1ste meervoud	we
;; 2de meervoud	jullie
;; 3de meervoud	ze

;; 1e, 2e en 3e vorm enkel-/meervoud
(def pv {:enkelvoud '("ik" #_ "je" "u" #_ "hij" "ze" "het")
         :meervoud  '("we" #_ "jullie" #_ "ze")})

;; Nederlands heeft regelmatige en onregelmatige werkwoorden.

;; Regelmatige werkwoorden volgen dezelfde vervoegeing.
;; Om een regelmatig werkwoord te vervoegen moet je de stam weten.
;; Voordat we gaan kijken naar de vervoeging van werkwoorden, moeten we daarom
;; eerst weten hoe we de stam kunnen afleiden van de infinitief.

;; Om een Nederlands werkwoord te kunnen vervoegen, moet je eerst weten hoe je de stam kunt maken.

;; Om de stam te vinden moet je eerst de infinitief hebben. De infinitief is de 'onvervoegde' vorm
;; van het werkwoord, de vorm die in woordenboeken staat: werken, zien, weten enz.

;; De onbepaalde wijs of (Romaans) infinitief is een werkwoordsvorm die niet vervoegd is naar persoon
;; of getal (zie ook persoon en getal). Vooral in het onderwijs wordt de infinitief ook wel "het hele werkwoord" genoemd.
;; In het Nederlands wordt de infinitief in het algemeen gevormd door aan de stam de uitgang -en toe te voegen.
;; Eventueel wordt daarbij een medeklinker verdubbeld of stemhebbend gemaakt of een dubbele klinker enkel geschreven:
;; buig-en, lop-en, schrijv-en, werk-en.
;; Er is één werkwoord met twee infinitieven, namelijk zijn/wezen.

;; De infinitieven van Nederlandse werkwoorden eindigen steeds op -en.
(def infinitief '(\e \n))
(defn infinitief? [woord] (= (take-last 2 woord) '(\e \n)))

;; De uitzonderingen zijn zijn, gaan, staan en slaan.
(def uitzonderingen-infinitief '("gaan" "staan" "slaan"))


;; Ook doen en zien moeten tot deze uitzonderingen worden gerekend, omdat oe en ie samen een klinker vormen.
;; Verdere uitzonderingen zijn dezelfde werkwoorden als ze een voorvoegsel hebben, zoals ondergaan, verstaan, ontzien enz.

(def onvervoegde-werkwoorden '("werken" "weten" "zien" "weten" "lopen"))

;; De algemene regel om te stam te vinden is:
;; stam = infinitief min '-en' We noemen dit de ruwe stam, omdat de stam vaak nog een beetje aangepast moet worden.


(infinitief? "lopren")
(infinitief? "schapen") ; wrong!

(defn ruwe-stam [inf] (take 2 (reverse inf)))

(ruwe-stam "werken")



;; Een paar regels voor de stam:

;; Infinitieven met een lange klinker hebben ook een stam met een lange klinker
;; Een stam eindigt nooit op twee dezelfde medeklinkers

  ;; ‘Medeklinkers is groter dan klinkers’ : ‘Medeklinkers’ is een langer woord dan ‘klinkers’, dus er zijn er meer medeklinkers dan klinkers.
  ;; Er is slechts een kleine groep klinkers, a, e, i, o, u. De rest is een medeklinker.
  ;; Doen je tanden, tong of lippen mee? Dan is het een medeklinker, olé!
  ;; AEIOU: Spreek dit hardop uit. Als je bij de A begint is je mond wijd open, bij de volgende klinkers word je mond steeds kleiner.
  ;; Oei Au Als je jezelf slaat met een hamer zeg je AU en als je per ongeluk iemand anders met een hamer slaat zeg je OEI

;; Een stam eindigt nooit op een v of een z
;; De stam van een '-iën-werkwoord' eindigt op ie




;; parsers

;; For templates and snippets whose sources are not read dynamically, you can opt for another parser either locally:
;(deftemplate ugh {:parser xml-parser}
; (java.io.StringReader. "<a><div>hello</div></a>")
;  [])
; or globally for the declaring ns:
;(set-ns-parser! xml-parser)

;; The following selector + function is going to replace any ${var} in text and attributes by the value found in the map (or any function).
;;[:#container any-node] (replace-vars {:name "world" :class "hello"})

;; A step is a predicate, for example :h1, :p.some-class or even
;; (attr? :lang).

;; To select elements which match several predicates, you need to group
;; predicates into a vector: inside steps, vectors mean “and”. This may seem
;; confusing but the rule is simple: the outer-most vector hierarchically
;; chains steps, all other vectors denote intersection (and) between steps.

;; So [:p (attr? :lang)] is going to match any elements with a lang attribute
;; inside a p element. On the other hand, [[:p (attr? :lang)]] is going to match
;; any p with a lang attribute.