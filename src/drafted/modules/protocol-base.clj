
;; Basics

(defprotocol AProtocol
  "A protocol is a named set of named methods and their signatures, defined using defprotocol;
  defprotocol will automatically generate a corresponding interface, with the same name as the protocol"
  (bar [a b] "bar docs")
  (baz [a] [a b] [a b c] "baz docs"))


;; Note that you do not need to use this interface with deftype , defrecord , or reify, as they support protocols directly:
(defprotocol P
  (foo [x])
  (bar-me [x] [x y]))

(deftype Foo [a b c]
  P
  (foo [x] a)
  (bar-me [x] b)
  (bar-me [x y] (+ c y)))

(bar-me (Foo. 1 2 3) 42)



;; A Java client looking to participate in the protocol can do so most efficiently by implementing the protocol-generated interface.

;; External implementations of the protocol (which are needed when you want a class or type
;; not in your control to participate in the protocol) can be provided using the extend construct:

;;(extend AType
;;   AProtocol
;;    {:foo an-existing-fn
;;     :bar (fn [a b] ...)
;;     :baz (fn ([a]...) ([a b] ...)...)}
;;   BProtocol
;;     {})
