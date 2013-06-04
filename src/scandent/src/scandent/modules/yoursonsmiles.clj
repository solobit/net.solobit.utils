; Speed
; Cheshire is about twice as fast as data.json.
(ns scandent.modules.yoursonsmiles
  (:require [clojure.repl :refer [find-doc source dir]]
            [cheshire.core :refer :all]
            [cheshire.parse :as parse]
            [cheshire.factory :as factory])

  (:import java.util.Date))

(use 'clojure.java.io)
(println (slurp "/tmp/foo"))

(binding [factory/*json-factory* (factory/make-json-factory
                                  {:allow-non-numeric-numbers true})]
  (decode "{\"foo\":NaN}" true))

(dir cheshire.core)

;; generate some json
(generate-string {:foo "bar" :baz 5})

;; write some json to a stream
(generate-stream {:foo "bar" :baz 5} (clojure.java.io/writer "/tmp/foo"))

;; generate some SMILE
(generate-smile {:foo "bar" :baz 5})

;; generate some JSON with Dates
;; the Date will be encoded as a string using
;; the default date format: yyyy-MM-dd'T'HH:mm:ss'Z'
(generate-string {:foo "bar" :baz (Date. 0)})

;; generate some JSON with Dates with custom Date encoding
(generate-string {:baz (Date. 0)} {:date-format "yyyy-MM-dd"})

;; generate some JSON with pretty formatting
(println (generate-string {:foo "bar" :baz {:eggplant [1 2 3]}} {:pretty true}))

;; generate JSON escaping UTF-8
(generate-string {:foo "It costs £100"} {:escape-non-ascii true})
;; => "{\"foo\":\"It costs \\u00A3100\"}"

;; generate JSON and munge keys with a custom function
(generate-string {:foo "bar"} {:key-fn (fn [k] (.toUpperCase (name k)))})
;; => "{\"FOO\":\"bar\"}"

;; parse some SMILE (keywords option also supported)
;(parse-smile <your-byte-array>)

;; parse a stream (keywords option also supported)
(parse-stream (clojure.java.io/reader "/tmp/foo"))

;; parse a stream lazily (keywords option also supported)
(parsed-seq (clojure.java.io/reader "/tmp/foo"))

;; parse a SMILE stream lazily (keywords option also supported)
;(parsed-smile-seq (clojure.java.io/reader "/tmp/foo"))

;; In 2.0.4 and up, Cheshire allows passing in a function to specify what kind of types to return, like so:

;; In this example a function that checks for a certain key
(decode "{\"myarray\":[2,3,3,2],\"myset\":[1,2,2,1]}" true
        (fn [field-name]
          (if (= field-name "myset")
            #{}
            [])))
;; => {:myarray [2 3 3 2], :myset #{1 2}}
;; The type must be "transient-able", so use either #{} or []



(decode "111111111111111111111111111111111.111111111111111111111111111111111111")


;; => 1.1111111111111112E32 (a Double)

(binding [parse/*use-bigdecimals?* true]
  (decode "111111111111111111111111111111111.111111111111111111111111111111111111"))
;; => 111111111111111111111111111111111.111111111111111111111111111111111111M (a BigDecimal)





;; Custom encoders allow you to swap out the api for the fast
;; encoder with one that is slightly slower, but allows custom
;; things to be encoded:


;; First, add a custom encoder for a class:
; (encode java.awt.Color
;              (fn [c jsonGenerator]
;                (.writeString jsonGenerator (str c))))

;; There are also helpers for common encoding actions:
;(add-encoder java.net.URL encode-str)

;; List of common encoders that can be used: (see generate.clj)
;; encode-nil
;; encode-number
;; encode-seq
;; encode-date
;; encode-bool
;; encode-named
;; encode-map
;; encode-symbol
;; encode-ratio

;; Then you can use encode from the custom namespace as normal
;(encode (java.awt.Color. 1 2 3))
;; => "java.awt.Color[r=1,g=2,b=3]"

;; Custom encoders can also be removed:
;(remove-encoder java.awt.Color)

;; Decoding remains the same, you are responsible for doing custom decoding.


