(ns net.solobit.utils.macros)

(defmacro synonym [s s′]
  (list 'defmacro s′ (str s) '[& rest]
     (list 'cons (list 'quote s) 'rest)))

