(ns backgammon.game)

(defn create-board [] {:game (vec (repeat 24 [])) :home[] :bar[]})

(defn create-stone [player] {:owner player})


(defn- do-in [board area-key f]
  (let [area (area-key board)]
  (assoc board area-key (f area))))


(defn put-to-play [board stone pos]
  (do-in board :game
    #(assoc % pos (conj (nth % pos) stone))))

(defn put-to-home [board stone]
  (do-in board :home #(conj %1 stone)))

(defn put-to-bar[board stone]
  (do-in board :bar #(conj %1 stone)))

(defn remove-from-play[board pos]
  (do-in board :game
    (fn[game] (assoc game pos (pop (nth game pos))))))

(defn head-at[board pos]
  (peek (nth (:game board) pos)))

(defn move[board from to]
  (let[stone (head-at board from)
       cleared-board (remove-from-play board from)]
    (put-to-play cleared-board stone to)))


(defn- add-stones[board pos player amount]
  (let[stone (create-stone player)]
  (loop [b board i amount]
    (if (> i 0) (recur (put-to-play b stone pos) (dec i)) b))))

(defn- init-board-for-player[board player positions]
  (loop [b board entries (seq positions)]
    (if (not (empty? entries))
      (let [offset (key (first entries))
            amount (val (first entries)) ]
      (recur (add-stones b offset player amount) (rest entries)))
      b)))

(defn init-board [board player-one player-two]
  (loop [b board
        players [player-one player-two]
        positions [{0 2  11 5  16 3  18 5} {23 2  12 5  7 3  5 5}]]
    (if (not (empty? players))
      (recur (init-board-for-player b (first players) (first positions)) (rest players) (rest positions))
      b)))


(defn print-board [board]
  (let[game (:game board)
       size (count game)
       middle (/ size 2)
       half (split-at middle game)
       maxi 5
       print-range (fn [r] (println (apply str (map #(format "%03d  " (inc %1)) r))))
       to-row (fn [index cols] (interpose "  " (map #(:owner (nth %1 index {}) "[.]") cols)))]
    (print-range (range middle size))
    (dotimes [i maxi] (println (apply str (to-row i (last half)))))
    (println (apply str (repeat (* middle 1) "- -  ")))
    (dotimes [i maxi] (println (apply str (to-row (- maxi i 1) (reverse (first half))))))
    (print-range (reverse (range 0 middle)))
    (println (str "home: "  (:home board)))
    (println (str "bar:  "  (:bar board)))))

(print-board b)
(def b (create-board))
(def b (put-to-play b (create-stone "[J]")  0))
(def b (put-to-home b (create-stone "[J]")))
(def b (put-to-bar b (create-stone "[J]")))
(dotimes [i 5] (def b (put-to-play b (create-stone "[F]") 23)))


