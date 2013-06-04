(ns tt
  (:use net.solobit.utils.defrecord2)
  (:require [fipp.printer :refer (pprint-document)]))

(defrecord2 Player [symbol name stack])
(new-player {:symbol 'inc :name "Rob" :stack 15})

(defrecord2 Move [player from to])

(new-move {:player :p1 :from [1 1] :to [2 2]})

(defrecord2 Strategy [player goals])
(defrecord2 Goal [describe success failure])


(def a (new-strategy {:player :p1 :goals [(new-goal {:describe "Fill 7th spot" :success '(if (= 1 1) true false)})
 (new-goal {:describe "Fill 7th spot" :success '(if (= 1 1) true false)})
 (new-goal {:describe "Fill 7th spot" :success '(if (= 1 1) true false)})
 (new-goal {:describe "Fill 7th spot" :success '(if (= 1 1) true false)})]}))





(defn ppd [doc]
  (pprint-document doc {:width 10}))

(ppd [:span "One" :line "Two" :line "Three"])

(ppd [:group "(do" [:nest 2 :line "(step-1)" :line "(step-2)"] ")"])