; http://thinkrelevance.com/blog/2009/08/12/rifle-oriented-programming-with-clojure-2

; Any comparison of hot JVM languages is likely to note that “Clojure is not object-oriented.”
; This is true, but it may lead you to the wrong conclusions. It’s a little like saying that a
; rifle is not arrow-oriented. In this article, you will see some of the ways that Clojure
; addresses the key concerns of OO: encapsulation, polymorphism, and inheritance.



; Clojure has vectors,..
[1 2 3 4]

; ... which are accessed by integer indexes.
(get [:a :b :c :d :e] 2)

; Lists are singly-linked lists, and are enclosed with parentheses.
'(1 2 3 4 5)

; Lists are special: Not only are they data, they also act as the syntax for invoking functions.
(+ 1 2 3 4 5)

; Collections themselves act as functions. They take an argument which is the key/index to look up:
([:a :b :c :d :e] 2)

; Clojure has maps, which are key/value collections:
{:fname "Stu", :lname "Halloway"}

; Sets contain a set of values, and their literal form is preceded with a hash.
; Here is the set of English vowels, using backslash to introduce a character literal:
#{\a \e \i \o \u}

([\a \b \c] 1)

([0 -2 0 1 0 0] 1)
(map neg? [0 -2 0 1 0 0])

 ({:name "Stu" :ext 101} :name)