;; clojure.tools.namespace
;; https://github.com/clojure/tools.namespace

;; Tools for managing namespaces in Clojure. Parse ns declarations from source files,
;; extract their dependencies, build a graph of namespace dependencies within a project,
;; update that graph as files change, and reload files in the correct order.

;; There's only one important function, refresh:

(comment "
  user=> (use '[clojure.tools.namespace.repl :only (refresh)])
  nil

  user=> (refresh)
  :reloading (com.example.util com.example.app com.example.app-test)
  :ok
  ")

;; Reloading Code: Preparing Your Application

;; Being able to safely destroy and reload namespaces without breaking your application
;; requires some discipline and careful design. It won't "just work" on any Clojure project.

;; No Global State

;; The first rule for making your application reload-safe is no global state.
;; That means you should avoid things like this:

;BAD! (def state-of-world (ref {}))
;BAD! (def object-handle (atom nil))

;; c.t.n.repl/refresh will destroy those Vars when it reloads the namespace (even if you used defonce).

;; Instead of storing your state in global Vars, store it locally in an object that represents
;; the running state of your application. Then provide a constructor function to initialize that state:

(defn create-application []
  "Constructor to initialize the locally scoped program state (wrapped inside)."
  {:state-of-world (ref {})
   :object-handle (atom nil)})

;; You can choose what representation works best for your application: map, vector, record, or even just
;; a single Ref by itself.

;; Typically you'll still need one global def somewhere, perhaps in the REPL itself, to hold the
;; current application instance. See "Managing Reloads" below.

;; Managed Lifecycle

;; The second rule for making your application reload-safe is having a consistent way to start and stop
;; the entire system. I like to do this with a protocol implemented by each major component in the system,
;; but smaller applications can probably get along fine with just a pair of functions.