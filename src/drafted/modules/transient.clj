;; If a tree falls in the woods, does it make a sound?
;; If a pure function mutates some local data in order to produce an immutable return value, is that ok?

(def x {:a 1 :b 2})
(def y [1 2 3 4])
(def z #{1 2 :a :b}); (hash-set \D \i \t)

(transient x)
(transient y)
(transient z)

;; It's an interesting question. Clojure data structures use mutation every time you call, e.g. assoc, creating one or more arrays and mutating them, before returning them for immutable use thereafter. The reason is performance - you simply can't get as fast using only pure functions and immutable data. Once constructed and shared however, being immutable and persistent is essential to robust programs. The things Clojure mutates internally are small, newly allocated arrays that constitute the internal nodes of its data structures. No one ever sees the arrays.

;; You run into a similar scenario, at a higher level, when you want to initialize or transform a large persistent data structure using multiple steps, none of which will be seen by any code other than the constructing/transforming code. The challenge here is that the source of a transformation will be an existing persistent data structure, and the result of the function will be shared. Copying into a traditional mutable data structure and back involves O(n) copying, and the internal code is an imperative mess quite unlike the rest of your Clojure code. Furthermore, there are no guards against accidentally sharing or aliasing the mutable data structure, especially if you need to call helper functions to do the work. In short, it would be a shame if you had to leave Clojure's model in order to speed up a piece of code like this. Transient data structures are a solution to this optimization problem that integrates with the Clojure model and provides the same thread safety guarantees you expect of Clojure.

;; How they work

;; Transient data structures are always created from an existing persistent Clojure data structure.

;; As of Clojure 1.1.0, vectors, hash-maps, and hash-sets are supported. Note that =not= all Clojure data structures can support this feature, but most will. Lists will not, as there is no benefit to be had.

;; You obtain a transient 'copy' of a data structure by calling transient. This creates a new transient data structure that is a copy of the source, and has the same performance characteristics. In fact, it mostly is the source data structure, and highlights the first feature of transients - creating one is O(1). It shares structure with its source, just as persistent copies share structure.

;; The second feature of transients is that creating one does not modify the source, and the source cannot be modified via use of the transient. Your source data is immutable and persistent as always.

;; Transients support the read-only interface of the source, i.e. you can call nth, get, count and fn-call a transient vector, just like a persistent vector.

;; Transients do not support the persistent interface of the source data structure. assoc, conj etc will all throw exceptions, because transients are not persistent. Thus you cannot accidentally leak a transient into a context requiring a persistent.

;; Transients support a parallel set of 'changing' operations, with similar names followed by ! - assoc!, conj! etc. These do the same things as their persistent counterparts except the return values are themselves transient. Note in particular that transients are not designed to be bashed in-place. You must capture and use the return value in the next call. In this way, they support the same code structure as the functional persistent code they replace. As the example will show, this will allow you to easily enhance the performance of a piece of code without structural change.

;; When you are finished building up your results, you can create a persistent data structure by calling persistent! on the transient. This operation is also O(1). Subsequent to calling persistent!, the transient should not be used, and all operations will throw exceptions. This will be true also for any aliases you might have created.

