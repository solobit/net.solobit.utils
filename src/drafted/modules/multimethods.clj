;; testing for dispatch value matches on class
(defmulti foo class)
(defmethod foo clojure.lang.PersistentVector [c] :a-vector)
(defmethod foo String [s] :a-string)
(defmethod foo clojure.lang.Keyword [s] :a-keyword)


(derive ::p1 ::player)
(derive ::p2 ::player)

(parents ::p1)

(isa? ::p1 ::player)

;; testing for dispatch value matches on class
(defmulti foo class)
(defmethod foo clojure.lang.PersistentVector [c] :a-vector)
(defmethod foo String [s] :a-string)
(defmethod foo clojure.lang.Keyword [s] :a-keyword)

;; superimpose new taxonomies on the existing Java class hierarchy
(derive java.util.Map ::collection)
(derive java.util.Collection ::collection)
(isa? java.util.HashMap ::collection)

(defmulti new-player :Player)
(defmethod new-player :Player1 [n] n)
(defmethod new-player :Player2 [n] n)
