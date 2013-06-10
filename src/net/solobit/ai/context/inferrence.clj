(ns net.solobit.ai.context.inferrence
  (:refer-clojure :exclude [extend])
  (:use [clj-time.core]))

(comment "
  Morning moves into afternoon at noon. However, there are no absolute set times for when afternoon becomes evening,
  when evening becomes night or when night moves into morning. Although the sources appear to contradict each other,
  either could be considered correct because it is all down to the viewpoint of the individual.

  As a general rule, assuming you aren't working a night shift or other strange hours:
  ------------------------------------------------------------------------------------

  Say Good morning any time between getting up and noon.

  Say good afternoon any time between noon and 5pm

  Say good night any time after 5pm and bedtime as a farewell.

  Say good night any time after 9pm and going to bed.")

(defn morning?
  "Returns true if the hour is between 0400 and 1200 LT."
  []
  (let [h (hour (now))]
    (if (and (> h 4)
             (< h 12))
      true false)))

(defn noon?
  "Returns true if the hour is between 1200 and 1700 LT."
  []
  (let [h (hour (now))]
    (if (and (> h 12)
             (< h 17))
      true false)))

(defn night?
  "Returns true if the hour is between 1700 and 0500 LT."
  []
  (let [h (hour (now))]
    (if (and (> h 17)
             (< h 5))
      true false)))

;; TODO Web cam measure coming closer and moving away
;; TODO Pick
;; (htref http://nakkaya.com/2011/01/24/lane-detection-using-clojure-and-opencv/)
;; (htref http://nakkaya.com/2010/01/19/clojure-opencv-detecting-movement/)
(def *action* "Going to bed")
(def *motion* :leaving)



(defmulti greeting #(% "language"))

(defmethod greeting "English" [params]
 "Hello!")

(defmethod greeting "French" [params]
 "Bonjour!")

(defmethod greeting :default [params]
 (throw (IllegalArgumentException.
          (str "I don't know the " (params "language") " language"))))

(def english-map {"id" "1", "language" "English"})
(def french-map {"id" "2", "language" "French"})
(def spanish-map {"id" "3", "language" "Spanish"})

(greeting english-map)
(greeting french-map)
;(greeting spanish-map)

