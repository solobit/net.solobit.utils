
(def game
  (ref {"game"
        {:board {"term1" [0 0 0 0 0 0] , "term2" [0 0 0 0 0 0]}
         :players {"p1" "Player 1" , "p2" "Player2" , :p3 1} "moves" 0}}))


(dosync
 (alter game
        assoc-in ["game" :board "term3"]
        0))

(dosync
 (alter game
        update-in ["game" :board "term3"]
        inc))

(dosync
 (alter game
        assoc-in ["game" :players :p3]
        "Rob"))



