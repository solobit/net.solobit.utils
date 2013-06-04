;; Sets
;; Sets are collections of unique values.

;; There is literal support for hash-sets:
#{:a :b :c :d}

;; You can create sets with the hash-set and sorted-set functions:
(hash-set :a :b :c :d)

(sorted-set :a :b :c :d)

;; You can also get a set of the values in a collection using the set function:
(set [1 2 3 2 1 2 3])

;; Sets are collections:
(def s #{:a :b :c :d})
(conj s :e)

(count s)

(seq s)


(= (conj s :e) #{:a :b :c :d :e})

;; Sets support 'removal' with disj, as well as contains? and get, the latter
;; returning the object that is held in the set which compares equal to the key, if found:
(disj s :d)

(contains? s :b)

(get s :a)

;; Sets are functions of their members, using get:
(s :b)

(s :k)

(def x #{:a :b :c :d})
(def y #{:d :e :f :g})

;; note only 1 :d
(clojure.set/union x y)

(clojure.set/difference x y)
(clojure.set/difference y x)
(clojure.set/difference y)

; ahh there you are
(clojure.set/intersection x y)


;; Clojure provides basic set operations like union/difference/intersection,
;; as well as some pseudo-relational algebra support for 'relations', which are
;; simply sets of maps - select/index/rename/join.