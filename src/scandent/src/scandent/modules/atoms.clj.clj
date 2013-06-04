(defn put
  "adds to atom-map either a key/value or another map"
  ([atom-map key value] (swap! atom-map assoc key value))
  ([atom-map map2] (swap! atom-map merge map2)))


