;;; Tip #1
;;; A vector of maps
(def some-maps [{:x 1 :y 2} {:x 2 :y 1} {:x 1 :y 4} {:x 2 :y 8}])

;;; Sort the maps first on :x and then on :y

(defn sort-maps-by
  "Sort a sequence of maps (ms) on multiple keys (ks)"
  [ms ks]
  (sort-by #(vec (map % ks)) ms))

(sort-maps-by some-maps [:x :y])

;;; Tip #2
;;; When you type something like (iterate inc 1) on the REPL (or any
;;; kind of infinite, lazy sequence) the REPL will try to evaluate the
;;; whole thing and will never finish. One way to print some parts of
;;; an infinite sequence on the REPL is to do this on the REPL and
;;; then try to print the sequence -
;;; (set! *print-length* 10)
;;; (iterate inc 1)
;;; Which will only print the first 10 items of the above infinite
;;; sequence -
;;; (1 2 3 4 5 6 7 8 9 10 ...)
;;; There is also *print-level* which can be used to determine how
;;; nested/recursive data-structures are printed on the REPL

(ns tips
  ;; requires clojure 1.2 if you are on 1.1.x, use this instead
  ;; (:require [clojure.contrib.duck-streams :as io])
  (:require [clojure.contrib.io :as io]))

;;; Tip #3
;;; Use of the -> & ->> threading macros.
(defn word-freq
  "Calculate a frequency map of words in a text file."
  [f]
  (take 20 (->> f
                io/read-lines
                (mapcat (fn [l] (map #(.toLowerCase %) (re-seq #"\w+" l))))
                (remove #{"the" "and" "of" "to" "a" "i" "it" "in" "or" "is"})
                (reduce #(assoc %1 %2 (inc (%1 %2 0))) {})
                (sort-by (comp - val)))))

;;; Run it like this (word-freq "/path/to/file.txt")
