(ns ants.core
  (:refer-clojure :exclude [alter commute ref-set ensure])
  (:use [net.cgrand.megaref
         :only [alter commute ref-set ensure megaref subref]]))

(def world
  (megaref (vec
            (repeat 4 (vec
                       (repeat 6 0))))))

@world

(defn place [xy]
  (subref world xy))

(place [1 2])


(defstruct ant :dir) ;may also have :food

(defn create-ant
  "create an ant at the location, returning an ant agent on the location"
  [loc dir]
    (sync nil
      (let [p (place loc)
            a (struct ant dir)]
        (alter p assoc :ant a)
        (agent loc))))

;(create-ant [1 1] (rand-int 8))

ant