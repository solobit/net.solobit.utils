;; Simple macro to run things 10 times and average the result
(defmacro bench [& body]
  `(/ (reduce
       +
       (for [i# (range 10)]
         (->> (with-out-str (time (do ~@body)))
              (drop (inc (count "Elapsed time: ")))
              (take-while #(not (= % \space)))
              (apply str)
              (Double/parseDouble)))) 10))

;; Loop/recur: 1 million in 2.7msecs
(bench (loop [x 0]
         (if (< x 1000000)
           (recur (inc x))
           x)))

;; Loop with function: 1 million in 24 msecs
(bench (defn countup [x]
         (if (< x 1000000)
           (recur (inc x))
           x))
       (countup 0))

;; Loop with pre- tests: 33 msecs
(bench (defn countup [x]
         {:pre [(>= x 0)]}
         (if (< x 1000000)
           (recur (inc x))
           x))
       (countup 0))

;; Lazy creation and GC: 155ms
(bench (first (drop 1000000 (iterate inc 0))))

;; Atom swaps: 1 million in 69ms
(bench (def a (atom 0))
       (while (> 1000000 @a)
         (swap! a inc)))

;; Atom swaps in a hashmap: 86ms
(bench (def m {:a (atom 0)})
       (while (> 1000000 @(:a m))
         (swap! (:a m) inc)))

;; Atom swaps in a bigger hashmap: 91ms
(bench (def m {:a (atom 0)
               :b (atom 0)
               :c (atom 0)})
       (while (> 1000000 @(:a m))
         (swap! (:a m) inc)))

;; Two threads, two atoms: 104ms
(bench (def a (atom 0))
       (def b (atom 0))
       (def fut (future (while (> 1000000 @b)
                          (swap! b inc))))
       (while (> 1000000 @a)
         (swap! a inc))
       @fut)

;; Working on independent atoms test: 270msecs
(bench (def m {:a (atom 0)
               :b (atom 0)})
       (def fut (future (while (> 1000000 @(:a m))
                          (swap! (:a m) inc))))
       (while (> 1000000 @(:b m))
         (swap! (:b m) inc))
       @fut)

;; Watchers: 1 million x 2 atom swaps and a watcher call in 172ms
(bench (def a (atom 0))
       (def b (atom 0))
       (add-watch a :a (fn [k r o n] (swap! b inc)))
       (while (> 1000000 @a)
         (swap! a inc)))

;; Ref alters: 1 million in 1694ms
(bench (def a (ref 0))
       (while (> 1000000 @a)
         (dosync (alter a inc))))

;; Agent looping: 1 million in 1529ms
(bench (def agt (agent 0))
       (defn countup [x]
         (when (> 1000000 x)
           (send *agent* countup))
         (inc x))
       (send agt countup)
       (while (> 1000000 @agt)
         (Thread/sleep 1)))

;; Agent looping with send-off (1987ms)
(bench (def agt (agent 0))
       (defn countup [x]
         (when (> 1000000 x)
           (send-off *agent* countup))
         (inc x))
       (send-off agt countup)
       (while (> 1000000 @agt)
         (Thread/sleep 1)))

;; Agent ping-pong; 1 million back-forth loops in (2973 ms)
(bench (def agt1 (agent 0))
       (def agt2 (agent 0))
       (defonce to-agt2 nil)
       (defn to-agt1 [x]
         (when (> 1000000 x)
           (send agt1 to-agt2))
         (inc x))
       (defn to-agt2 [x]
         (when (> 1000000 x)
           (send agt2 to-agt1))
         (inc x))
       (send agt1 to-agt2)
       (while (> 1000000 @agt1)
         (Thread/sleep 1)))

;; Non-atom future loop (5814ms)
(bench (loop [a 0]
         (let [f (future (inc a))]
           (if (> 1000000 @f)
             (recur @f)
             @f))))

;; Agent queuing and await (8213ms)
(bench (def agt (agent 0))
        (while (> 1000000 @agt)
          (send agt inc))
        (await agt))

;; Future round-trips: 1 million in 25 secs (7281ms)
(bench (def a (atom 0))
       (while (> 1000000 @a)
         (let [f (future (swap! a inc))]
           @f)))

;; Promise-deliver on a single thread (354ms)
(bench (dotimes [_ 1000000]
         (let [p (promise)]
           (deliver p 1))))

;; Promise/deliver roundtrip in a future: 1 million (7949)
(bench (def a (atom 0))
       (while (> 1000000 @a)
         (let [p (promise)]
           (future (deliver p 1))
           @p)
         (swap! a inc)))

;; Atoms + futures: 1 million in (67ms)
(bench (def a (atom 1))
       (def f1 (future (while (> 1000000 @a)
                         (swap! a inc))))
       @f1)

;; Atoms being hammered with two futures: 1 million in (248ms)
(bench (def a (atom 1))
       (def f1 (future (while (> 1000000 @a)
                         (swap! a inc))))
       (def f2 (future (while (> 1000000 @a)
                         (swap! a inc))))
       @f1)

;; Three futures: 1 million in (376ms)
(bench (def a (atom 1))
       (def f1 (future (while (> 1000000 @a)
                         (swap! a inc))))
       (def f2 (future (while (> 1000000 @a)
                         (swap! a inc))))
       (def f3 (future (while (> 1000000 @a)
                         (swap! a inc))))
       @f1)

;; Two threads hammering on a ref (1670ms)
(bench (def a (ref 0))
       (def fut (while (> 1000000 @a)
                  (dosync (alter a inc))))
       (while (> 1000000 @a)
         (dosync (alter a inc))))

;; Two threads hammering on a ref commutatively (1771ms)
(bench (def a (ref 0))
       (def fut (while (> 1000000 @a)
                  (dosync (commute a inc))))
       (while (> 1000000 @a)
         (dosync (commute a inc))))

;; Refs on hashmap, single thread: (5001ms)
(bench (def m (ref {:a 0 :b 0}))
       (while (> 1000000 (:a @m))
         (dosync (commute m assoc :a (inc (:a @m)))))
       (while (> 1000000 (:b @m))
         (dosync (commute m assoc :b (inc (:b @m))))))

;; refs on Hashmap test, commuting: (17499ms)
(bench (def m (ref {:a 0 :b 0}))
       (def fut (future (while (> 1000000 (:a @m))
                          (dosync (commute m assoc :a (inc (:a @m)))))))
       (while (> 1000000 (:b @m))
         (dosync (commute m assoc :b (inc (:b @m))))))

;; refs on Hashmap test, single-thread (4470ms)
(bench (def m (ref {:a 0 :b 0}))
       (while (> 1000000 (:a @m))
                  (dosync (alter m assoc :a (inc (:a @m)))))
       (while (> 1000000 (:b @m))
         (dosync (alter m assoc :b (inc (:b @m))))))

;; refs on Hashmap test, non-commuting: (15170ms)
(bench (def m (ref {:a 0 :b 0}))
       (def fut (future (while (> 1000000 (:a @m))
                          (dosync (alter m assoc :a (inc (:a @m)))))))
       (while (> 1000000 (:b @m))
         (dosync (alter m assoc :b (inc (:b @m))))))