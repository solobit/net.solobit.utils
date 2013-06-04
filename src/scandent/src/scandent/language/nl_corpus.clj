

;;

(ns scandent.language.nederlands
  (:require [net.cgrand.enlive-html :as html]))

(def ^:dynamic *url-nl-corpus* "http://www.let.rug.nl/vannoord/bin/alpinods_dir?./cdb")


;; dynamic fetch
(defn fetch-url [url]
  (html/html-resource (java.net.URL. url)))


;; selectors

;wikitext
(defn nl-sterk []
  (map html/text
       (html/select
        (fetch-url *url-nl-corpus*)
        [:table.gray :td])))

(defn print-tekst
"Infinitief, verleden tijd enkelvoud,	verleden tijd meervoud,	voltooid deelwoord, vervoegingspatroon"
  [] (doseq [line
             (map (fn [[inf vte vtm vdw vp]]
                         (assoc {} :inf inf :vte vte :vtm vtm :vdw vdw :vp vp))
                             (partition 5 (nl-sterk)))]
        (println line)))

;(print2)
