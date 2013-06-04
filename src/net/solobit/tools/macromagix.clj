(ns net.solobit.tools)

(defmacro synonym [s s′]
  (list 'defmacro s′ (str s) '[& rest]
     (list 'cons (list 'quote s) 'rest)))

(synonym defmacro ≜m)
(synonym defn ≜λ)
(synonym def ≜)
(synonym fn λ)
(synonym letfn letλ)

(synonym comment todo)
(synonym comment goal)
(synonym comment redo)
(synonym comment prio)
(synonym comment note)
(synonym comment bugs)
(synonym comment flaw)
(synonym comment fail)
(synonym comment good)
(synonym comment pro)
(synonym comment con)

(pro "Some list of positive aspects:
     + it was good
     + that was best
     + some other reason")

(con "Some remarks for improvement:
     - next time you should
     - please watch out for
     - don't use this")

(fail "`(1 2 3)")
(good "'(1 2 3)")

;(clojure.repl/dir ibdknox.tools.reader)










