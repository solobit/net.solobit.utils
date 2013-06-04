
(ns foo)

(defprotocol Greeter
  (greet [g, name]))

(defrecord Game [id started finished duration winner style players])

(defrecord Player [name]
  Greeter
  (greet [_, subject] (str "Hello " subject ", I am " ".")))

(defrecord Tactics [tacid name description pro con drills])

(defrecord Strategy [stratid name description pro con planning momentum])

(Player. "rob")

