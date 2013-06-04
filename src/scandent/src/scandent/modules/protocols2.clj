;; Protocols

;; Protocols were introduced in Clojure 1.2.

;; Motivation

;; Clojure is written in terms of abstractions. There are abstractions for
;; sequences, collections, callability, etc. In addition, Clojure supplies many
;; implementations of these abstractions. The abstractions are specified by host
;; interfaces, and the implementations by host classes. While this was
;; sufficient for bootstrapping the language, it left Clojure without similar
;; abstraction and low-level implementation facilities. The protocols and
;; datatypes features add powerful and flexible mechanisms for abstraction and
;; data structure definition with no compromises vs the facilities of the host
;; platform.

;; There are several motivations for protocols:
;; * Provide a high-performance, dynamic polymorphism construct as an
;;   alternative to interfaces
;; * Support the best parts of interfaces
;;  * specification only, no implementation
;;  * a single type can implement multiple protocols
;; * While avoiding some of the drawbacks
;;  * Which interfaces are implemented is a design-time choice of the type
;;    author, cannot be extended later (although interface injection might
;;    eventually address this) implementing an interface creates an
;;    isa/instanceof type relationship and hierarchy
;; * Avoid the 'expression problem' by allowing independent extension of the set
;;   of types, protocols, and implementations of protocols on types, by
;;   different parties
;;  * do so without wrappers/adapters
;; * Support the 90% case of multimethods (single dispatch on type) while
;;   providing higher-level abstraction/organization
;;
;; Basics
;;
;; A protocol is a named set of named methods and their signatures, defined
;; using defprotocol:

(defprotocol AProtocol
  "A doc string for AProtocol abstraction"
  (bar [a b] "bar docs")
  (baz [a] [a b] [a b c] "baz docs"))

;; * No implementations are provided
;; * Docs can be specified for the protocol and the functions
;; * The above yields a set of polymorphic functions and a protocol object
;;  * all are namespace-qualified by the namespace enclosing the definition
;; * The resulting functions dispatch on the type of their first argument, and
;;   thus must have at least one argument
;; * defprotocol is dynamic, and does not require AOT compilation

;; defprotocol will automatically generate a corresponding interface, with the same name as the protocol, i.e. given a protocol my.ns/Protocol, an interface my.ns.Protocol. The interface will have methods corresponding to the protocol functions, and the protocol will automatically work with instances of the interface.
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


(foo
 (let [x 42]
   (reify P
     (foo [this] 17)
     (bar-me [this] x)
     (bar-me [this y] x))))


;; A Java client looking to participate in the protocol can do so most
;; efficiently by implementing the protocol-generated interface.

;; External implementations of the protocol (which are needed when you want a
;; class or type not in your control to participate in the protocol) can be
;; provided using the extend construct:

; (extend AType
;   AProtocol
;    {:foo an-existing-fn
;     :bar (fn [a b] ...)
;     :baz (fn ([a]...) ([a b] ...)...)}
;   BProtocol
;     {...}
; ...)

;; extend takes a type/class (or interface, see below), a one or more protocol +
;; function map (evaluated) pairs.
;; * Will extend the polymorphism of the protocol's methods to call the supplied
;;   functions when an AType is provided as the first argument
;; * Function maps are maps of the keywordized method names to ordinary fns
;;  * this facilitates easy reuse of existing fns and maps, for code
;;    reuse/mixins without derivation or composition
;; * You can implement a protocol on an interface
;;  * this is primarily to facilitate interop with the host (e.g. Java)
;;  * but opens the door to incidental multiple inheritance of implementation
;;    * since a class can inherit from more than one interface, both of which
;;      implement the protocol
;;    * if one interface is derived from the other, the more derived is used,
;;      else which one is used is unspecified. The implementing fn can presume
;;      first argument is instanceof AType You can implement a protocol on nil
;;      To define a default implementation of protocol (for other than nil) just
;;      use Object

;; Protocols are fully reified and support reflective capabilities via extends?
;; , extenders , and satisfies? .
;; * Note the convenience macros extend-type , and extend-protocol
;; * If you are providing external definitions inline, these will be more
;;   convenient than using extend directly

(extend-type MyType
  Countable
    (cnt [c] ...)
  Foo
    (bar [x y] ...)
    (baz ([x] ...) ([x y zs] ...)))

  ;expands into:

(extend MyType
  Countable
   {:cnt (fn [c] ...)}
  Foo
   {:baz (fn ([x] ...) ([x y zs] ...))
    :bar (fn [x y] ...)})
