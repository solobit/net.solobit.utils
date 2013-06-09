;; http://clojure.org/state


(ns net.solobit.domain.entities
  (:use [net.solobit.utils.defrecord2 :only [defrecord2]]
        [net.solobit.utils.megaref :only [megaref]]))


(defrecord2 Powerup [level bonus multipliers])

;; Logical identity via atomic references to values (Refs and Agents),
;; changes are controlled/coordinated by the STM.
(def x (megaref (new-powerup {:level 10 :bonus '(* 12 10 8) :multipliers 5})))

@x


(defrecord Employee2 [name surname])

(defrecord2 Employee [name surname])

; Namespace 2 in "my/queries.clj", where a defrecord is used
(ns my.queries
  (:require [net.solobit.domain.entities])
  (:import [net.solobit.domain.entities]))

;;"웃"

(clojure.repl/dir net.solobit.domain.entities)

;(dr/defrecord2 Emplox [foo bar])
;(new-emplox {:foo "Bar"})
;(new-employee2 {:name "Albert" :surname "Chang"})
;(dr/new-record (new-employee2 {:name "JH"}))

(println
  "Employees named Albert:"
  (filter #(= "Albert" (.name %))
    [(Employee2. "Albert" "Smith")
     (Employee2. "John" "Maynard")
     (Employee2. "Albert" "Cheng")]))