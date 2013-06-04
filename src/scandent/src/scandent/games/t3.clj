(ns game
    (:require [swiss-arrows.core :refer :all]))

(def matrix
  (ref [[0 0 0 0 0 0]
        [1 0 0 0 0 0]
        [0 0 0 0 0 0]
        [0 0 0 0 0 0]]))

(matrix 1)


(-> (matrix 1)
    (println))

(-> 25 Math/sqrt int list)




(defn roll [] (apply vector (sort (repeatedly 2 #(inc (rand-int 6))))))

(-<> [1 2 3]
     (update-in <> [1] inc))

;+->                  <--;
(def x [0 2 1 0 -2 -1])

x
(replace [\a 3 5 5 5 7] x)
