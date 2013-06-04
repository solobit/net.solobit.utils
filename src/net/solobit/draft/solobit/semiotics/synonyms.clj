(ns net.solobit.utils.semiotics.synonyms)

(defmacro synonym [s s′]
  (list 'defmacro s′ (str s) '[& rest]
     (list 'cons (list 'quote s) 'rest)))

(synonym defmacro ≜m)
(synonym defn ≜λ)
(synonym def ≜)
(synonym fn λ)
(synonym letfn letλ)

(synonym defmacro makro)
(synonym true ∴)

(defprotocol Singer
  (sing [g, song]))

(defrecord Tune [lyrics]
   Singer
  (sing [_, song] (str "Hello " song ", I am " ".")))

(synonym Tune. ♫)



(♫ "lalala")