(ns net.solobit.utils.logs
  (:use
    clojure.tools.logging
    clj-logging-config.log4j))

;; Remember that set-logger! mutates the configuration of logging in the JVM.
;; That's why there's a '!' in the function name, to indicate the side-effect.
(set-logger! :name "access-log")
(set-loggers!

    "com.malcolmsparks.foo"
    {:level :info :pattern "%m"}

    "com.malcolmsparks.bar"
    {:level :debug})


(set-logger! :pattern "%d - %m%n")
(set-logger! :level :warn)

(info "Just a plain logging message, you should see the level at the beginning")

(info "A logging message, just the message this time")


(info "A logging message with the date in front")
