In Clojure 1.2, you can destructure the rest argument just like you would destructure a map. This means you can do named non-positional keyword arguments. Here is an example:

(defn blah [& {:keys [key1 key2 key3]}] (str key1 key2 key3))

(blah :key1 "Hai" :key2 " there" :key3 10)

(blah :key1 "Hai" :key2 " there")

(defn blah [& {:keys [key1 key2 key3] :as everything}] everything)

(blah :key1 "Hai" :key2 " there")

;; Anything you can do while destructuring a Clojure map can be done in a function's argument list as shown above.
;; Including using :or to define defaults for the arguments like this:

(defn blah [& {:keys [key1 key2 key3] :or {key3 10}}] (str key1 key2 key3))

(blah :key1 "Hai" :key2 " there")

;; But this is in Clojure 1.2. Alternatively, in older versions, you can do this to simulate the same thing:

(defn blah [& rest] (let [{:keys [key1 key2 key3] :or {key3 10}} (apply hash-map rest)] (str key1 key2 key3)))

(blah :key1 "Hai" :key2 " there")

;; and that works generally the same way.

;; And you can also have positional arguments that come before the keyword arguments:

(defn blah [x y & {:keys [key1 key2 key3] :or {key3 10}}] (str x y key1 key2 key3))

(blah "x" "Y" :key1 "Hai" :key2 " there")

;; These are not optional and have to be provided.

;; You can actually destructure the rest argument like you would any Clojure collection.

(defn blah [& [one two & more]] (str one two "and the rest: " more))
(blah 1 2 "ressssssst")

;; You can do this sort of thing even in Clojure 1.1. The map-style destructuring for keyword arguments only came in 1.2 though.

;(defn set-dice [& {:keys [dice1 dice2 dice3 dice4] :or {dice1 [1 2 3 4 5 6]}}]
;  (into {} (filter first {dice1 dice2 dice3 dice4})))
;(into {} (filter second record))
;(rand-nth (:d1 record))
;(set-dice)
