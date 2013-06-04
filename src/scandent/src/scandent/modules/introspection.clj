
(ns scandent.modules.introspection
  (:use clojure.pprint clojure.repl)
  (:require [clojure.set]
            [clojure.reflect]))

(defn which-ns?
  "Return a string representation of the value set in special *earmuffs*
  variable *ns*. Usually these can be rebound."
  [] (str *ns*))


(defn reflect-declared-methods
  "All three do the same: find declared methods."
  []
     (.getDeclaredMethods (.getClass {:a 1})) ;1

     (-> {:a 1} .getClass .getDeclaredMethods pprint) ;2

     (-> clojure.lang.PersistentArrayMap
         .getDeclaredMethods
         pprint));3


(dir clojure.reflect)


(apropos #"^ref")

(def a {})
(clojure.reflect/reflect a)

(find-doc #"^ref")