
(ns trictrac
  (:use [clojure.contrib.core]))


(def ^:dynamic *stones* 15) ; number of checkers that every player starts with


;; Strategies

(def defensive {:board {:s1 [0 0 0 0 0 0]
                  :s2 [0 0 0 0 0 0]
                  :s3 [0 0 0 0 0 0]
                  :s4 [0 0 0 0 0 0]}})


(def game

  (ref {"game"

        ;; For clarity, use literal
        {:board {:s1 [0 0 0 0 0 0]
                 :s2 [0 0 0 0 0 0]
                 :s3 [0 0 0 0 0 0]
                 :s4 [0 0 0 0 0 0]}

         ;; So the idea is, both players would be able to suggest
         ;; and confirm (both) or the match won't start (you could also randomize this for fun)
         :constraints {:field-max 5
                       :field-min -5
                       :stones-max 15
                       :stones-min 0}

         :dices {:d1 (range 1 7)
                 :d2 (range 1 7)}

         :players {:p1 {:name "Player 1"
                        :stack *stones*} ;=> (:p1 :p1 :p1 ... n)

                   :p2 {:name "Player 2"
                        :stack *stones*}}
         "moves" 0}}))

(take 1 (repeat 15 :p1))

(defn get-board
  "Returns section(s) of the board or the entire board if none."
  ([] (get-in @game ["game" :board]))
  ([s1] (get-in @game ["game" :board :s1])))
  ;([& {:keys [s1 s2 s3 s4] (get-in @game ["game" :board key1])))

  (defn x [& sections] sections)

(get-board :s1)

(defn add-player
  "Adds a new player by key and name and stack with stones."
  [key name]
  (dosync
   (alter game
          assoc-in ["game" :players key]
          {:name name
           :stack *stones*})))


(defn delete-player
  "Removes a player from the game by key."
  [key]
  (dosync
   (alter game
          dissoc-in ["game" :players key])))


@game
;(instellen :username "Jogn")
;(add-player :p3 "Rob")

;(delete-player :p3)



(defn stack
  "Applies function (dec/inc) on stack of player k"
  [op player] (dosync (alter game update-in ["game" :players player :stack] (take 1))))


(defn- roll-base
  "The base dice roll using two 6 sided dices with equal values."
  [] (sort (take 2 (repeatedly #(inc (rand-int 6))))))


(defn roll-dice
  "Rolls the dice and checks the outcome against cases.
  If either trictrac (1 2) or double, returns mod values."
  [] (let [base (roll-base)]

       ;; Again, for clarity
       (case base
         ((1 2)) '(1 1 2 2 5 5 6 6)
         ((2 2)) '(2 2 5 5)
         ((3 3)) '(3 3 4 4)
         ((4 4)) '(4 4 3 3)
         ((5 5)) '(5 5 2 2)
         ((6 6)) '(6 6 1 1)
         base)))
















;(flatten (repeatedly 10 #(worp)))





;; CLIPBOARD ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defmacro <<
  [string]
  `(:p2 ~@(:p2 string)))

;; Predicates (not used atm)

(defn doubles?
  "Checks to see if a double was rolled."
  [dice]
  (if (list? dice)
    (if (= (first dice)
           (last dice))
      true)))

(defn trictrac?
  "Checks if a roll 'trictrac', 1 and 2 dice is."
  [dice]
  (if (list? dice)
    (if
      (and (= (first dice) 1)
           (= (last dice) 2))
      true)))







(defmacro >>
  [string]
  (let [dice (sort (take 2 (repeatedly #(inc (rand-int 6)))))
        bonus (if (= (first dice) (last dice))
                (repeat 2 (apply list (first dice) (- 7 (first dice))  ))
                (if (and (= (first dice) 1) (= (last dice) 2))
                  "trictraccccccccccc"))]

  `(str ~@(str "Player 1 rolls: " (first dice) " and " (last dice)))))



;; Idea: (>> "agressive")
;; (<< "defensive")


(defrecord Board [name])

(def board [0 0 0 0 -1 0])

(defn which-are-occupied? [player]
  (case player
    :p1 (map pos? board)
    :p2 (map neg? board)
    (map zero? board)))

(which-are-occupied? :p2)


(defn tt-generator
  [pairs freq]
  (let
    [tpl #(sort (repeatedly pairs #(inc (rand-int 6))))
     seq (repeatedly freq #(tpl))
     mod (case tpl
           ((1 2)) '(1 1 2 2 5 5 6 6)
           ((2 2)) '(2 2 5 5)
           ((3 3)) '(3 3 4 4)
           ((4 4)) '(4 4 3 3)
           ((5 5)) '(5 5 2 2)
           ((6 6)) '(6 6 1 1)
           tpl)

     all (reduce #(assoc %1 %2 (inc (%1 %2 0))) {} seq) ]
    tpl))


(defn roll-dice [qty]
  "Quantity refers to the amount of dice thrown in 1 roll.
  Some games have you roll 1, others 2 or 3 dice at once."
  (let [f #(inc (rand-int 6))]
    (repeatedly qty f)))

(comment "Some may require you to sort the dice.")

(sort (roll-dice 2))

(comment "Which in turn, may form pairs or tuples, sorted or not.")

;; Lazy sequence, use doall to evaluate

(doall (repeatedly 100 #(sort (roll-dice 2))))

