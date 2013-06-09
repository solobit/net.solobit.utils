(ns scandent.core)

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

(ns net.solobit.domain.entities
  (:use [net.solobit.utils.defrecord2 :only [defrecord2]]))

(defrecord Employee [name surname])

(defrecord2 Employee2 [name surname])

; Namespace 2 in "my/queries.clj", where a defrecord is used
(ns my.queries
  (:require [net.solobit.domain.entities])
  (:import [net.solobit.domain.entities]))

(clojure.repl/dir net.solobit.domain.entities)

;(dr/defrecord2 Emplox [foo bar])
;(new-emplox {:foo "Bar"})
;(new-employee2 {:name "Albert" :surname "Chang"})
;(dr/new-record (new-employee2 {:name "JH"}))

(println
  "Employees named Albert:"
  (filter #(= "Albert" (.name %))
    [(Employee. "Albert" "Smith")
     (Employee. "John" "Maynard")
     (Employee. "Albert" "Cheng")]))