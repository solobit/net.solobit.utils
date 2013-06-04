(ns universe)
;; The first important term to define is time. Simply put, time refers to the
;; relative moments when events occur. Over time, the properties associated
;; with an entity— both static and changing, singular or composite—will form a
;; concrescence (Whitehead 1929) and be logically deemed its identity.

#_timeline

"tick tack"
1
"something"
2
"some other thing happens (or another entity is born)"
(defrecord Baby [name])
3
"clock keeps ticking"
(def feeling-of-time "passing along")
4
"who am I?"
5 'T "moment <--- snapshot"

6
(comment "Garbage collection ~> die!")
"..."
'n
#_end-of-the-universe


;; It follows from this that at any given time, a snapshot can be taken of an
;; entity’s properties defining its state. This notion of state is an immutable
;; one because it’s not defined as a mutation in the entity itself, but only
;; as a manifestation of its properties at a given moment in time.

(comment "
  |____|
  |:a 1|
  |:b 2| <--- snapshot moment defines the state (the entity still has the same identity)
  |2may|
  |____|
  |:a 2|  | linear time flowing
  |:b 2|  V
  |3may|
  ")

(comment "This text inside the file are properties and every letter I type,
  which takes time, we change one of the properties of this file (entity),
  and text (in an open and used file) happens to mutate rapidly (non-static)
  yet its name could be considered somewhat static from this viewpoint.


  Clojure’s implementation attempts to draw a clear separation between an object’s state and identity as they relate to time. To state the difference to Clojure’s model in terms of the aforementioned flip book, the mutable state model is different, as seen in figure 1.3.

  Immutability lies at the cornerstone of Clojure, and much of the implementation ensures that immutability is supported efficiently. By focusing on immutability, Clojure eliminates entirely the notion of mutable state (which is an oxymoron) and instead expounds that most of what’s meant by objects are instead values. Value by definition refers to an object’s constant representative7 amount, magnitude, or epoch. You might ask yourself: what are the implications of the value-based programming semantics of Clojure?")

;; Imagine a child’s flip book, to understand the terms fully.

;; It’s important to note that in the canon of object-oriented programming,
;; there’s no clear distinction between state and identity. In other words,
;; these two ideas are conflated into what’s commonly referred to as mutable
;; state.