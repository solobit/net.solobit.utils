(ns scandent.modules.generators
 (:require [clojure.core :as core]
           [clojure.data :as data]
           [clojure.data.generators :refer [uniform]]
           [clojure.repl :as repl :refer [doc find-doc dir]]
           [clojure.test :refer (deftest is)]))


(dir clojure.repl)

(dir clojure.data.generators)

(defn reservoir-sample
  "Reservoir sample ct items from coll, using *rnd*."
  [ct coll]
  (loop [result (transient (core/vec (take ct coll)))
         n ct
         coll (drop ct coll)]
    (if (seq coll)
      (let [pos (uniform 0 n)]
        (recur (if (< pos ct)
                 (assoc! result pos (first coll))
                 result)
               (inc n)
               (rest coll)))
      (persistent! result))))

(reservoir-sample 3 '(3 6 78 3 2 7 89 7 3))

