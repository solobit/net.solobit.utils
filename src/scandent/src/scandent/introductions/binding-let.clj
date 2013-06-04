
(defmacro rfor [bindings body]
  (let [bindings (partition-all 2 bindings)
        lbinding (last bindings)
        bindings (butlast bindings)]
    (reduce
     (fn [form [n vs]]
       `(mapcat (fn [~n] ~form) ~vs))
     `(map (fn [~(first lbinding)] ~body) ~(last lbinding))
     (reverse bindings))))

;; Dynamic binding means that the code inside your binding form and any
;; code which that code calls (even if not in the local lexical scope) will see the new binding.

; (def x 0) ;=> java.lang.IllegalStateException: Can't dynamically bind non-dynamic var: user/x

(def ^:dynamic x 0)
(def y 0)

;; let creates a lexically scoped immutable alias for some value.
(let [x 1] var-get #'x)

;; binding creates a dynamically scoped binding for some Var.
(binding [x 1] (var-get #'x))

;; binding actually creates a dynamic binding for a Var but let only shadows the var with a local alias:

;; binding can use qualified names (since it operates on Vars), let can't
(binding [user/x 1] (var-get #'x))
; (let [user/x 1] (var-get #'x)) ;=> Invalid local name!

; let-introduced bindings are not mutable. binding-introduced bindings are thread-locally mutable:

(binding [x 1] (set! x 2) x)

;(let [x 1] (set! x 2) x) ;=> Can't set local var...

;; IN SHORT (only the below will actually persist)

;; Lexical vs. dynamic binding:
(defn foo [] (println x))
(binding [x 1] (foo)) ;=> 1 printed

;(let [x 1] (foo)) ;=> 0