(def a-to-j (vec (map char (range 65 75))))

(nth a-to-j 4)
(get a-to-j 4)
(nth a-to-j 19 :whoops)
(get a-to-j 19 :whoops)

(seq a-to-j)
(rseq a-to-j)

(assoc a-to-j 4 "no longer E")

(replace {2 :a, 4 :b} [1 2 3 2 3 4])

(def matrix
  [[1 2 3]
   [4 5 6]
   [7 8 9]])

(get-in matrix [1 2])

(assoc-in matrix [1 2] 'x)

(update-in matrix [1 2] * 100)

; given yx location in equilateral 2d matrix, returns sequence of
; locations surrounding it


(defn neighbours
  ([size yx] (neighbours [[-1 0] [1 0] [0 -1] [0 1]] size yx))
  ([deltas size yx]
     (filter (fn [new-yx]
               (every? #(< -1 % size) new-yx))
             (map #(map + yx %) deltas))))

a-to-j
(subvec a-to-j 3 6) ; note: exclusive: starts at 3, but ends *before* 6

(first {:width 10, :height 20, :depth 15})
(vector? (first {:width 10, :height 20, :depth 15}))

(conj [:a :b :c] (rest {:width 10, :height 20, :depth 15}))

(doseq [[dimension amount] {:width 10, :height 20, :depth 15}]
  (println (str (name dimension) ":") amount "inches"))

(name :hey)


(#{:a :b :c :d} :c)

(#{:a :b :c :d} :e)

(get #{:a 1 :b 2} :b)

(get #{:a 1 :b 2} :nothing-doing)

(some #{:b} [:a 1 :b 2])
(some #{1 :b} [:a 1 :b 2])
(some #{9} [1 2 3 4 :a :b :c])

(sorted-set :b :c :a)
(sorted-set [3 4] [1 2])
(sorted-set [4 3] [1 2])

(contains? #{1 2 4 3} 4)
(contains? [1 2 4 3] 4)

(clojure.set/intersection #{:humans :fruit-bats :zombies}
                          #{:chupacabra :zombies :humans})

(clojure.set/intersection #{:pez :gum :dots :skor}
                          #{:pez :skor :pocky}
                          #{:pocky :gum :skor})

(clojure.set/union #{:humans :fruit-bats :zombies}
                   #{:chupacabra :zombies :humans})

(clojure.set/union  #{:pez :gum :dots :skor}
                    #{:pez :skor :pocky}
                    #{:pocky :gum :skor})


(clojure.set/difference #{1 2 3 4} #{3 4 5 6})

(hash-map :a 1, :b 2, :c 3, :d 4, :e 5)

(let [m {:a 1, 1 :b, [1 2 3] "4 5 6"}]
  [(get m :a) (get m [1 2 3])])

(let [m {:a 1, 1 :b, [1 2 3] "4 5 6"}]
  [(m :a) (m [1 2 3])])

(seq {:a 1, :b 2})

(into {} [[:a 1] [:b 2]])

(into {} (map vec '[(:a 1) (:b 2)]))
(into {} (map vec ['(:a 1) '(:b 2)]))



