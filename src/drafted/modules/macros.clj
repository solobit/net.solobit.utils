(ns net.solobit.utils
  (:use [clojure.algo.monads]
        [clojure.pprint :only (pprint)]))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; http://goo.gl/t4jOn
;;

;; Second, the gripe macro adds a warning to w, and returns nil. gripe is
;; intended to be used when bailing out of a function called with bad arguments.

(defmacro gripe [warning]
  `(do
     (dosync (alter w conj ~warning))
     nil))

(defmacro good-for [[idx start stop] & body]
    `(loop [~idx ~start limit# ~stop]
       (if (< ~idx limit#)
         (do
     ~@body
     (recur (inc ~idx) limit#)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; http://goo.gl/YW1f2
;;
(defmacro debug-map
  "Map quoted expressions to their values."
  [& exprs]
  `(zipmap (reverse (list ~@(map (fn [expr] `'~expr) exprs)))
           (reverse (list ~@(map (fn [expr] expr) exprs)))))

(defmacro debug-list
  "List-associate expressions with their values."
  [& exprs]
  `(list ~@(map (fn [expr] `(list '~expr ~expr)) exprs)))

(defmacro debug [& exprs]
  "Pretty-print expression-value associations."
  `(pprint (debug-map ~@exprs)))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; http://goo.gl/UCbcu
;;
;; Common Lisp Object System (CLOS)
(def ^:dynamic this)

(defn method-call [klass m-name args]
  (if klass
    (let [m (m-name (klass :methods))]
      (if m
        (apply m args)
        (method-call (klass :parent) m-name args)))
    (throw (RuntimeException. (str "Unable to find method named " m-name)))))

(defn new-object [klass]
  (let [state (atom {})]
    (fn thiz [c & args]
      (binding [this thiz]
        (condp = c
          :class klass
          :class-name (klass :name)
          :set! (let [[k v] args]
                  (dosync
                    (alter state assoc k v)))
          :get (let [[k] args]
                 (state k))
          (method-call klass c args))))))

(defn new-class [class-name methods parent]
  (fn thiz [c & args]
    (condp = c
      :name (name class-name)
      :new (new-object thiz)
      :methods methods
      :parent parent)))

(defn method-spec [[mname args & body]]
  `{~(keyword mname) (fn ~args ~@body)})

(defmacro defclass [c-name & body]
  (let [m-specs (map rest (filter #(= (first %) 'method) body))
        methods (apply merge (map method-spec m-specs))
        parent (last (apply merge (filter #(= (first %)'extends) body)))]
    `(def ~c-name (new-class (keyword '~c-name) ~methods ~parent))))

;; examples

(defclass Animal
  (method make-sound [] (str "SOUND!")))

(defclass Person
  (extends Animal)
  (method say-hello [n] (str "Hello " n "!")))

(def siva (Person :new))

(siva :class-name)

(siva :make-sound)

(siva :say-hello "Sean")



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; http://goo.gl/QFhxi
;;
#_(anaphoric-if (some-computation 11)
              (* 2 it))

(defn some-computation [x] (if (even? x) false (inc x)))

(defmacro anaphoric-if [expr & body]
  `(if-let [~'it ~expr] (do ~@body)))

(defmacro anaphoric-and
  ([] true)
  ([x] x)
  ([x & more]
    `(anaphoric-if ~x
                   (anaphoric-and ~@more))))

(defmacro with-it [f a & more]
  `(let [~'it ~a]
     (~f ~'it ~@more)))

(defmacro a-if [a & more]
  `(with-it if ~a ~@more))

(defmacro thread-it
  ([x] x)
  ([x & more]
    `(let [~'it ~x]
       (thread-it ~@more))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; http://goo.gl/LLi6N
;;
#_ (my-let [x 10
            y x
            z (+ x y)]
           (* x y z))

(defmacro single-arg-fn [[v expr] & body]
  `((fn [~v] ~@body) ~expr))

(defmacro my-let [lettings & body]
  (let [helper (fn
          ([] `(do ~@body))
          ([v expr & more]
            `(single-arg-fn [~v ~expr]
                            (my-let ~more ~@body))))]
    (apply helper lettings)))

;; (extend-fn f [x y z] (* x y z))
(defmacro extend-fn [name args & body]
  `(let [old-fn# (var-get (var ~name))
         new-fn# (fn ~args (do ~@body))
         dispatcher# (fn [& more#]
                       (if (= ~(count args) (count more#))
                         (apply new-fn# more#)
                         (apply old-fn# more#)))]
     (alter-var-root (var ~name) (constantly dispatcher#))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; http://goo.gl/NC8of
;;
(defmacro defn-macro [name args body]
  `(def ~name (clojure.core/fn ~args ~body)))

(defmacro and-macro
  ([] true)
  ([x] x)
  ([x & more]
     `(let [a ~x]
        (if a
          (and-macro ~@more)
          a))))

(defmacro or-macro
  ([] nil)
  ([x] x)
  ([x & more]
     `(let [a ~x]
        (if a
          a
          (or-macro ~@more)))))

(defmacro when-macro [test & body]
  `(if ~test (do ~@body)))

(defmacro when-not-macro [test & body]
  `(if ~test nil (do ~@body)))

(defmacro when-let-macro [[form test] & body]
  `(let [temp ~test]
     (when temp
       (let [~form temp]
         ~@body))))

(defmacro while-macro [test & body]
  `(loop []
     (when ~test
       ~@body
       (recur))))

(defmacro ->-macro
  ([arg] arg)
  ([arg first-form] `(~(first first-form) ~arg ~@(rest first-form)))
  ([arg first-form & more-forms]
     `(->-macro (->-macro ~arg ~first-form) ~@more-forms)))





;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; http://goo.gl/odGlX
;;
(defmacro chain [x form]
  `(. ~x ~form))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; An anaphoric equivalent would have give you a syntax like:
;; (make foo 1 2 3 4 it 6 7 it 8 9)
(defmacro make [v & body]
   `(let [~'it ~v]
      (list ~@body)))

;; New syntax in Dutch
(defmacro plak [w & lichaam]
  `(let [~'vooraan ~w]
     (list ~@lichaam)))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; http://goo.gl/uHTFW
(defmacro randomly [& list]
  "randomly evaluates an argument"
  (when list
    (nth list (int (rand (count list))))))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
(defmacro synonym [s s′]
  (list 'defmacro s′ (str s) '[& rest]
     (list 'cons (list 'quote s) 'rest)))

(synonym defmacro ≜m)
(synonym defn ≜λ)
(synonym def ≜)
(synonym fn λ)
(synonym letfn letλ)

;; Fancy lambda signs
;(defmacro λ [& sigs] `(fn ~@sigs))
;(defmacro lambda [& sigs] `(fn ~@sigs))
;(defmacro defλ [& sigs] `(defn ~@sigs))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; http://goo.gl/NLhOR
;;
(defmacro futures
  [n & exprs]
  (->> (for [expr exprs]
         `(future ~expr))
    (repeat n)
    (mapcat identity)
    vec))


(defmacro wait-futures
  [& args]
  `(doseq [f# (futures ~@args)]
     @f#))





;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; http://goo.gl/PGm0R
;;
(defmacro follow [namespace req]
  `( ~(ns-resolve
       (symbol
        (str "httpfoo.decision." (name namespace))) 'start) ~req))

(defmacro ask
  [question arg]
  `(~(symbol (str "ask-" question)) ~arg))

(defn ask-beer [n] (str "Serving a cold brewsky! " n))

(ask "beer" 2)

(defmacro terminate
  [code arg]
  `(~'finish ~arg ~code))

(defmacro question
  [name positive-clause negative-clause]
  `(defn ~(symbol (str "ask-" name))
     [request#]
     (if (~name request#)
       (~@positive-clause request#)
       (~@negative-clause request#))))

(defmacro first-question
  [name positive-clause negative-clause]
  `(def ~'start
      (question ~name ~positive-clause ~negative-clause)))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Metrics

(defmacro timerun
  "Output the time a operation/calculation has taken."
  [& body]
 `(time (dotimes [_# 1000] ~@body)))


;; Testing

(defmacro err? [e & body]
  `(do ~@(map (fn [x] `(is (~'thrown? ~e ~x))))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; http://goo.gl/zGPoz
;;
(defmacro dbg [tag & msgs]
  "Debug output"
  `(println (format "[%s]" ~tag) ~@msgs))

(defmacro defresolve [nsp fnc]
  "Resolve and define private function"
  `(def ~fnc (ns-resolve '~nsp '~fnc)))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; http://goo.gl/PBYwR
;;
;; Direct mapping of describe-it style
(defmacro describe [description & body]
  `(do (js/describe ~description
                    (fn [] ~@body))))

(defmacro it [description & body]
  `(do (js/it ~description
              (fn [] ~@body))))

; Aliases for it - given and then
(defmacro given [description & body]
  `(do (js/describe ~description
                    (fn [] ~@body))))

(defmacro then [description & body]
  `(do (js/it ~description
              (fn [] ~@body))))

; Before and after
(defmacro before [& body]
  `(do (js/before (fn [] ~@body))))

(defmacro after [& body]
  `(do (js/after (fn [] ~@body))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; http://goo.gl/k03g1
;;
(defmacro returning
  [val-form & body]
  `(let [return# ~val-form]
     ~@body
     return#))

(defmacro realtime
  [expr]
  `(let [start# (System/currentTimeMillis)]
     ~expr
     (- (System/currentTimeMillis) start#)))

(defmacro realtimed
  [expr]
  `(let [start# (System/currentTimeMillis)
         ret#    ~expr
         time#  (- (System/currentTimeMillis) start#)]
     [ret# time#]))

(defmacro with-realtime
  [[binding-sym expr] form]
  `(let [[ret# ~binding-sym] (realtimed ~expr)]
     ~form
     ret#))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; http://goo.gl/wAVd6
;;
(defmacro unless [p body]
  `(if ~p nil ~body))
;; can't def unless fn, args are eval'd eagerly not lazily...

(defmacro unless-amit [expr form]
  (list 'if expr nil form))

(defmacro unless-do [expr & forms]
  `(if ~expr nil (do ~@forms)))

(defmacro foo [ & f]
  `(let [a# (inc 1)]
     (println 'a# 'f ~@f)))

(defmacro my-declare [ & vars]
  `(do ~@(map #(list 'def %) vars)))

(defmacro my-declare-2 [ & vars]
  `(do ~@(for [v# vars] (list 'def v#))))

(defn def-form [x]
  `(def ~x))

(defmacro my-declare-1 [ & vars]
  `(do ~@(map def-form vars)))

(defmacro infix [a op b]
  `(~op ~a ~b))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Logic
;;
(defmacro when [cond & exprs]
  `(if ~cond (do ~@exprs) nil))


(defmacro maybe-nil [& e]
  `(try ~@e (catch Exception _# nil)))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; http://goo.gl/XYQXI
;;
(defmacro when-> [argument condition body]
  `(if ~condition
     (-> ~argument ~body)
     ~argument))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; http://goo.gl/fymNw
(defmacro do-something-twice [f]
	`(do ~f ~f))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; http://goo.gl/SY9b1
(defmacro when-not-empty [field & body]
  `(when (not-empty ~field) ~@body))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; http://goo.gl/5xeFH
;;
;; Monad function alias
(defmonadfn m-bind_ [& steps]
  (reduce
    (fn [n p] (m-bind p (fn [_] n)))
    (reverse steps)))

(defmacro do     [steps expr] `(domonad ~steps ~expr))
(defmacro >>=    [mv f] `(m-bind ~mv ~f))
(defmacro >>     [& mvs] `(m-bind_ ~@mvs))
(defmacro return [v] `(m-result ~v))
(defmacro fmap   [f m] `(m-fmap ~f ~m))
(defmacro lift-m [n f] `(m-lift ~n ~f))
(defmacro join   [m] `(m-join ~m))