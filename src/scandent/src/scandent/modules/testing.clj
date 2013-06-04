; Own namespace defined.
(ns scandent.modules.testing
  ; Import the testing namespace of clojure.
  (:use clojure.test))

; Given functions, one may wish to write tests
; to easily notice when stuff breaks.
(defn add2 [x]
  (+ x 2))

; There are two (2) ways to define tests.

; Either define with the function definition itself:
(with-test
  (defn add2 [x] ; seperate def
    (+ x 2))
  (is (= 4 (add2 2))) ; is = assert
  (is (= 5 (add2 3))))

; Or second, using deftest macro:
(deftest test-adder
  (is (= 24  (add2 22))))

; Tests are about asserting stuff
(deftest test-something
  (is (= 1 1))) ; assert that 1 is equal to 1

; Tests can also be grouped together:
(deftest arithmetic
  (test-adder)
  (test-something))




;; done