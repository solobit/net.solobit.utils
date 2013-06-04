(ns net.solobit.tools)

(defmacro mrefs [uri]
  `(str "foo " ~uri))


(defmacro synonym [s s′]
  (list 'defmacro s′ (str s) '[& rest]
     (list 'cons (list 'quote s) 'rest)))

;(defn bar [x] (str "xxxx"))

(defmacro foo [x]
  (comment
    "If x is a list calling the function 'bar', then return :foobar
    , else return x as a string")
  (if (and (coll? x) (= (first x) 'bar)) ; when doesn't work if bar isnt defined
    :foobar
    `(str ~x)))


(defrecord Bevel [kvpor aan])
(defrecord Opdracht [wie wat waar wanneer waarmee waarom])

(def opdracht1 (Bevel. "Waarschuwingsbevel: uitvoeren mars over 10 minuten" "Sld. Jansen"))

opdracht1

(:aan opdracht1)