(ns foo
  (:use net.solobit.tools.defrecord2))

(defrecord2 RocketCar [color fuel name])

(new-rocket-car {:color "Blue" :fuel 100 :name "Zoomer"})
(new-rocket-car {:color "Blue"})

(new-rocket-car
  (new-rocket-car {:color "Blue" :fuel 100 :name "Zoomer"})
  {:color "Red" :name "Flash"})

(clojure.pprint/pprint (new-rocket-car {:color "Blue" :fuel 100 :name "Zoomer"}))