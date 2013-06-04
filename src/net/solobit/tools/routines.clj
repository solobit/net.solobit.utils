(ns net.solobit.tools
  (:refer-clojure :exclude [extend])
  (:use [clj-time.core]))

(defrecord Person [fname lname dob])
(defrecord Routine [name tasks])
(defrecord Task [time description])

(def mr (Routine. "Morning"
                  (Task. "07:00" "Arise")))

(defprotocol Assign-Task
  (plan-time [_]))

(defrecord Someone [nick-name assigned-task]
  Assign-Task (plan-time [_] (str nick-name " (assigned task " assigned-task "): uuumm")))

(def Rob (->Someone "Dude" "biertje drinken"))

(plan-time Rob)



(defmacro synonym [s s′]
  (list 'defmacro s′ (str s) '[& rest]
     (list 'cons (list 'quote s) 'rest)))


; Namespace 1 in "my/data.clj", where a defrecord is declared
(ns my.data)

(defrecord Employee [name surname])


; Namescape 2 in "my/queries.clj", where a defrecord is used
(ns my.queries
  (:require my.data)
  (:import [my.data Employee]))

(println
  "Employees named Albert:"
  (filter #(= "Albert" (.name %))
    [(Employee. "Albert" "Smith")
     (Employee. "John" "Maynard")
     (Employee. "Albert" "Cheng")]))







(comment "Create a solid scheme for morning and evening routines

Morning Routine

5:30 AM: Arise
Put on gym clothes, contacts, and stumble to kitchen
Drink a glass of ice cold water and protein shake
5:40 AM: Out the door for workout (Monday/Wednesday/Friday: Weights; Tuesday/Thursday: Interval Cardio; Saturday/Sunday: Walk)
6:40 AM: Return home and shower, brush teeth, etc.
6:55 AM: 20 minutes of meditation, prayer, and scripture study
7:15-ish AM: Review my goals and day’s schedule

Evening Routine

9:30 PM: Review day’s work, review goals (long term and short term), plan tomorrow’s schedule
10:00 PM: Get ready for bed; take vitamins.
10:15 PM: Write in journal
10:30 PM: Read a book
11:00 PM: Lights out.
")