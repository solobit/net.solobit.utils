
(ns scandent.modules.tricks)


(def toggle-states
  {:checked :unchecked
   :unchecked :checked
   nil :checked})

(defn toggle-value [m]
  (update-in m [:value] toggle-states))

(toggle-value {:checked nil})


; destructure the rest argument just like you would destructure a map
(defn blah [& {:keys [key1 key2 key3]}] (str key1 key2 key3))

(blah :key1 "Hai" :key2 " there" :key3 10)












