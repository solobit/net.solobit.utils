

(def game (atom {:board (vec (repeat 24 nil))}))

(:board @game)

(defn update-board []
  (swap! game :board [0 0]))

(vec (range 24))

(def some-chars (vec (map char (range 65 75))))

(def alphabet (vec (map char (range 65 91))))

(println (partition 6 (take 24 alphabet)))

(nth alphabet 1)

(def matrix (atom
     [[0 0 0 0 0 0]
      [0 0 0 0 0 0]
      [0 0 0 0 0 0]
      [0 0 0 0 0 0]]))

(defn update-field [brd section field] (update-in brd [(dec section) (dec field)] inc))

(def move1 (update-field @matrix 1 3))

(update-field move1 1 2)
