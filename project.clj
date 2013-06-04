(defproject
  net.solobit
  "0.1.0-SNAPSHOT"

  :url "http://solobit.net"

  :description
  "Library full of utility code, tools, examples,
  hands-on information, macros and models found online.
  All rights reserved to their respective authors."

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [processing-core/processing.core "0.1.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [org.clojure/data.generators "0.1.0"]
                 [org.clojure/tools.namespace "0.2.3"]
                 [org.clojure/data.generators "0.1.0"]
                 [org.clojars.nakkaya/vision "1.0.0"]
                 [org.clojure/core.logic "0.8.3"]
                 [net.mikera/core.matrix "0.7.2"]
                 [swiss-arrows "0.6.0"] ; monads -<>
                 [me.raynes/fs "1.4.0"] ; file system
                 [slingshot "0.10.3"]
                 [cheshire "5.2.0"]
                 [clj-time "0.5.1"] ; joda time
                 [clj-pdf "1.10.0"] ; reporting
                 [clj-logging-config "1.9.10"]
                 [enlive "1.1.1"] ; screen scraping
                 [fipp "0.3.0"]
                 [matchure "0.10.1"]
                 [markdown-clj "0.9.26"] ; render
                 [cc.artifice/clj-ml "0.3.5"] ; machine learning

                 [clojure-opennlp "0.2.0"
                  :exclusions [org.clojure/clojure]]

                 [org.clojars.gnarmis/snowball-stemmer ; sentiments
                  "0.1.1-SNAPSHOT"]

                 [org.clojure/core.unify "0.5.5"
                  :exclusions [org.clojure/clojure]]

                 [org.clojure/algo.monads "0.1.4"
                  :exclusions [[org.clojure/tools.macro]
                               org.clojure/clojure]]

                 [com.cemerick/pomegranate "0.2.0"
                  :exclusions [[commons-codec] [commons-io]
                               [org.apache.httpcomponents/httpcore]
                               org.apache.httpcomponents/httpclient]]])
