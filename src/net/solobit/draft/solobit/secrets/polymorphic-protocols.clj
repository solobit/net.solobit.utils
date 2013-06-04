(ns net.solobit.utils.secrets)
(comment "
Polymorphism is the ability of a function or method to have different
definitions depending on the type of the target object. Clojure provides
polymorphism via both multimethods and protocols, and both mechanisms are more
open and extensible than polymorphism in many languages.
")

(defprotocol Concatenatable
  "Define a protocol named `Concatenatable` that groups one or more functions
  (in this case only one, `cat`) that define the set of functions provided."
  (cat [this other]))

;; That means the function cat will work for any object that fully satisfies
;; the protocol Concatenatable.

(comment "Extend the protocol to the String class and define the specific
  implementation a function body that concatenates the argument other onto the
  string this.")

(extend-type String
  Concatenatable
  (cat [this other]
       (.concat this other)))

(cat "House" " of Leaves")
(.concat "a" "b")

;; We can also extend this protocol to another type:

(extend-type java.util.List
  Concatenatable
  (cat [this other]
       (concat this other)))

(type :p1)

;; thus the cat function can be called with either type as its first argument —
;; the appropriate implementation will be invoked.

(cat [1 2 3] [4 5 6])
(cat "foo " "bar")

;; Keywords normally can't be concatenated but we can simulate the same effect
;; if we think of them as just strings.
(extend-type clojure.lang.Keyword
  Concatenatable
  (cat [this other]
       (str this other)))

(cat :foo :bar)

(comment "
  Note that String was already defined (in this case by Java itself) before we
  defined the protocol, and yet we were still able to successfully extend the
  new protocol to it. This isn’t possible in many languages. For example, Java
  requires that you define all the method names and their groupings (known as
  interfaces) before you can define a class that implements them, a restriction
  that’s known as the expression problem.

THE EXPRESSION PROBLEM

  The expression problem refers to the desire to implement an existing set of
  abstract methods for an existing concrete class without having to change the
  code that defines either. Object-oriented languages allow you to implement an
  existing abstract method in a concrete class you control (interface
  inheritance), but if the concrete class is outside your control, the options
  for making it implement new or existing abstract methods tend to be sparse.

  Some dynamic languages such as Ruby and JavaScript provide partial solutions
  to this problem by allowing you to add methods to an existing concrete object,
  a feature sometimes known as monkey-patching.")
