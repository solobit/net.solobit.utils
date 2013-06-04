(ns net.solobit.tools)
;; Implementation Intentions

;; if...then

(defrecord II [situation response])

(def goal1 (II. "If I feel like drinking" "I will only allow myself 3 beers."))
(def goal2 (II. "If I want to play games" "I will only do so for an hour a day."))
(def goal3 (II. "If it is 9PM on sunday night" "I will plan for the week."))
(def goal4 (II. "If it is tuesday night 8PM" "I will do my administration and clear the (e)mailbox."))
(def goal5 (II. "If I want to smoke smoke" "I will only buy 20 euros a week."))
(def goal6 (II. "If I want to hack" "I will use/learn Clojure."))
(def goal7 (II. "If I want to learn" "I will only learn what is feasible and improves self."))
(def goal8 (II. "If I want to surf the web" "I will only do so for one 1 hour a day on fun/research stuff."))
(def goal9 (II. "If I go to sleep" "I will brush my teeth"))

