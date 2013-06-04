(ns tt.devel)

(def stack 0)

(def matrix
  [[0 0 0 0 0 0]
   [0 0 0 0 0 0]
   [0 0 0 0 0 0]
   [0 0 0 0 0 0]])

;; If player is free to move around
(defn free? [] (if (> stack 0) false true))


(defn finishable? [] (first matrix))

(get-in matrix [1 2])

(assoc-in matrix [1 2] 'x)

(update-in matrix [1 2] * 100)

(defn neighbours
  ([size yx] (neighbours [[-1 0] [1 0] [0 -1] [0 1]] size yx))
  ([deltas size yx]
     (filter (fn [new-yx]
               (every? #(< -1 % size) new-yx))
             (map #(map + yx %) deltas))))


(neighbours 3 [0 0])
(neighbours 3 [1 1])

; e.g. position 0,0 has neighbours 4,2 in `matrix`
(map #(get-in matrix %) (neighbours 3 [0 0]))