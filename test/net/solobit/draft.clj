(ns foobar
  (:refer-clojure :exclude [extend])
  (:use [net.solobit.utils.defrecord2]
        [clj-time.core])
  (:require [fipp.printer :refer (pprint-document)]))

(defn ppd [doc]
  (pprint-document doc {:width 10}))

(defprotocol Agenda
  (update [this agenda-item]))

(defrecord2 Task [id description due completion])

(clojure.pprint/pprint
 (new-task {:id (now)
            :description "Some task"
            :due (today-at 12 00)
            :completion "70%"}))
