(ns scandent.modules.monads
  (:require [swiss-arrows.core :refer :all]))

; The Diamond Wand - similar to -> or ->> except that the flow of execution is
; passed through specified <> positions in each of the forms.

(-<> 2
     (* <> 5)
     (vector 1 2 <> 3 4))


; The diamond wand also supports literals:


 ;; map
 (-<> 'foo {:a <> :b 'bar})

 ;; vector
 (-<> 10 [1 2 3 <> 4 5])

; Like -> & ->> interpret a symbol x as (x), -<> interprets x as (x <>)

(-<> :a
      (map <> [{:a 1} {:a 2}])
      (map (partial + 2) <>)
      reverse)

; Default Positioning Behavior

; If no <> position marker is found in a form within the Diamond Wand -<>, the default
; positioning behavior follows that of the -> macro. Likewise, if no position is specified
; in a form within the Diamond Spear -<>>, the default is has the positioning semantics of ->>.
;
(-<> 0 [1 2 3])

(-<> 0 (list 1 2 3))

(-<>> 4 (conj [1 2 3]))

(-<> 4 (cons [1 2 3]) reverse (map inc <>))

; Nil-shortcutting Diamond Wand

 (-?<> "abc"
       (if (string? "adf") nil <>)
       (str <> " + more"))

; Non-updating Arrows (for unobtrusive side-effecting)

; It is often expedient, in particular for debugging and logging, to stick a
; side-effecting form midway in the pipeline of an arrow.

; An adequately elegant solution for inserting side-action in what would otherwise
; be a difficult situation. As a bonus, the arrow-style macros (including the wand--
; the <> does not refer to any sort of binding, and does not act recursively so it
; is not anaphoric in the usual sense, if at all) do not rely on symbol capture,
; and therefore are arbitrarily nestable.

 (-> {:foo "bar"}
    (assoc :baz ["quux" "you"])
    (-!> :baz second (prn "-!> second got here"))
    (-!>> :baz first (prn "-!>> first got here"))
    (-!<> :baz second (prn "got<" <> ">here"))
    (assoc :bar "foo"))

; The Back Arrow

; This is simply ->> with its arguments reversed, convenient in some cases. It was
; suggested as an alternative to egamble/let-else.

 (<<-
  (let [x 'nonsense])
  (if-not x 'foo)
  (let [more 'blah] more))

; Cleanly express flow of control with branching arrows (instead of the nesting seen
; earlier)
;
(-< (+ 1 2)
    (->> vector (repeat 3))
    (-> (* 2) list)
    (list 4))



  "The board is a square of 4 by 6, since there are always 2 players,
  we may use pos for player 1 and neg for player 2. Since any field may
  most hold 5 pieces, their max/min values are +/-5."


; Test out different board setups
(def board (atom {:board1 (partition 6 (repeat 24 0)) ; atomic using repeat 4 and repeat 6 in vectors
                  :board2 (seq ; non-atomic sequence of zeros partitioned per 6
                              (repeat 4
                                     (apply vector
                                            (repeat 6
                                                    0))))
                  :stacks {:p1 15 :p2 15}

            }))

;(swap! (:p1 (:stacks @board)))


; easily switch player by reversing the play field (perspective / movement direction)
(reverse (:board2 @board))


(partition 2 '(1 2 3 4 5))

;(nth board 1) ; first sector
;(nth (nth board 2) 2) ; second field of second sector

;(-< (+ 2 3)
    ;(->> ))



;
;
;