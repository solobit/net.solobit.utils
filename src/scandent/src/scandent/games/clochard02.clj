(ns scandent.games.clochard)



(map neg? [0 -2 0 1 0 0])
(ns foo)

(def dim-board   [60 60])

;; Track over time, groups of atoms
(def winner (ref "Player 1"))
(def points (ref 30000))
@winner

(dosync
 ;; ref-set allows you to alter the value of a Ref outright
	  (ref-set winner "Player 2")
     ;; alter command takes Ref and a function to apply to the current
     (alter points (partial + 15000)))



;; (def board
;;      (for [x (range (dim-board 0))]
;;        (for [y (range (dim-board 1))]
;;          [(if (< 50 (rand-int 100)) :on :off) x y])))


(def board
  (for [x (range (dim-board 0))]
    [0 x]))



(def players
  (atom
   {:p1 {:name "Player 1" :stack 15}
    :p2 {:name "Player 2" :stack 15}}))

;; Cause illegal state exception on stack exceeding 15
;; :validator function will be executed whenever atom attempts to change state:
(def p1-stack (atom 15 :validator #(> % -1)))
(def p2-stack (atom 15 :validator #(> % -1)))

;; apply swap! functions
(defn add-to-stack [stack] (swap! stack inc))
(defn add-to-game [stack] (swap! stack dec))
;(add-to-game p1-stack) ;=> Exception

;; decrease near 0 to test
(swap! p1-stack (partial + -14))
(swap! p1-stack dec)

;(defn strike [p1 p2 l1 l2]

(defn assoc-conj
  "Associate a key with a value in a map. If the key already exists in the map,
  a vector of values is associated with the key."
  [map key val]
  (assoc map key
    (if-let [cur (get map key)]
      (if (vector? cur)
        (conj cur val)
        [cur val])
      val)))

(def x {1 2 3 4 5 6})
x
(assoc-conj {:a 1 :b 2 :c [1 2 3]} :c 1)


(deref players) ;; fetch value of atom
@players  ;; @ shortcut
((:p1 @players) :name)


