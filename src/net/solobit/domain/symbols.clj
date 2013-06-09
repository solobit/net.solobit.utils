(ns net.solobit.domain.symbols
  (:refer-clojure :exclude [extend])
  (:use [clj-time.core]
        [net.solobit.utils.defrecord2 :only [defrecord2]]
        [net.solobit.utils.megaref :only [megaref]]))



(int \☹)



(comment "♥ life
(int \♪)
(int \♫)
(int \♯)
(int \⚔)
(int \⌨)
(int \✉)
(int \✎)
(int \‼)
(int \‽)
(int \⁇)
(int \⁈)
(int \ø)
  ✂ cut
  ° degree
  ⇱ left top corner
  ⇲ right bottom corner
  ⇵ transaction
  ⇉ parallel
  ⇈ parallel upstream
  ↯ trace
  ▵ delta
  ⅰ
  ⅱ
  ⅲ
  ✸ notice
  € euro
  ℕ noun
  ℮ event
  ☄ emit
  ➹ trigger
  ϟ signal
  ➟ next
  © copyright
  ✔ agreed
  ✘ fail
  ☐ open
  ☑ checked
  ☒ disabled/failed
  ∞ eternity

")


;(def built-in-formatter (formatters :basic-date-time))
;(def custom-formatter (formatter \"yyyyMMdd\"))

(doall (map #(char %) (range 8560 8570)))
;(char 8560)
;(str (char (+ 0 (int \☐))) " " (now) " Some task")



(defrecord2 Task [status created from to due description title])

(defn t [a b c]
  (new-task {:status 9744
             :created (now)
             :from "Rob"
             :to a
             :description b
             :title c}))

(t "Maria" "do laundry" nil)






;;  (let [status {:open (char 9744)
;;                :done (char 9745)
;;                :fail (char 9746)}
;;        date (now)]
;;    (str (:open status) " " date " " task))

;; (new-task "Something to do")


;(clojure.repl/dir clj-time.core)

(let [fruit ["apple" "orange" "grapes" "bananas"]]
  (concat (rest fruit) [(first fruit)]))