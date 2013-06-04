(defproject
  net.solobit
  "0.1.0-SNAPSHOT"

  :url "http://solobit.net"

  :description
  "Library full of utility code, tools, examples, hands-on information, macros
  and models found online. All rights reserved to their respective authors."

  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [

                 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                 ;; Core
                 ;;

                 [org.clojure/clojure "1.5.1"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [org.clojure/tools.namespace "0.2.3"]

                 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                 ;; Support tools
                 ;;

                 [slingshot "0.10.3"] ; better try..catch

                 [com.cemerick/pomegranate "0.2.0"
                  :exclusions [[commons-codec] [commons-io]
                               [org.apache.httpcomponents/httpcore]
                               org.apache.httpcomponents/httpclient]]

                 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                 ;; Data
                 ;;

                 [net.mikera/core.matrix "0.7.2"]
                 [org.clojure/data.generators "0.1.0"]
                 [enlive "1.1.1"] ; screen scraping
                 [matchure "0.10.1"] ; pattern matching
                 [cheshire "5.2.0"] ; JSON fast

                 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                 ;; Rendering, parsers, readers
                 ;;

                 [markdown-clj "0.9.26"] ;; Markdown to HTML and vice versa

                 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                 ;; Visualize, draw, charts
                 ;;

                 [processing-core/processing.core "0.1.0"]


                 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                 ;; Artificial Intelligence (AI)
                 ;;

                 ;; vision
                 [org.clojars.nakkaya/vision "1.0.0"]
                 [org.clojure/core.incubator "0.1.2"]
                 [org.clojars.nakkaya/jna "3.2.7"]
                 ;; logic
                 [org.clojure/core.logic "0.8.3"]
                 [org.clojure/core.unify "0.5.5" :exclusions
                  [org.clojure/clojure]] ; github.com/clojure/core.unify
                 ;; perception
                 [clj-time "0.5.1"]
                 ;; natural language
                 [clojure-opennlp "0.2.0" :exclusions [org.clojure/clojure]]
                 [org.clojars.gnarmis/snowball-stemmer "0.1.1-SNAPSHOT"]
                 ;; machine learning
                 [cc.artifice/clj-ml "0.3.5"]

                 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                 ;; Lambda, math, statistics
                 ;;

                 ;; monads -<>
                 [swiss-arrows "0.6.0"]
                 [org.clojure/algo.monads "0.1.4" :exclusions
                  [[org.clojure/tools.macro] org.clojure/clojure]]

                 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                 ;; Platform and file / operating systems
                 ;;

                 [me.raynes/fs "1.4.0"]

                 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                 ;; Reporting, logging, printing
                 ;;

                 [clj-pdf "1.10.0"] ; reporting
                 [clj-logging-config "1.9.10"] ; powerful thread/shared logs
                 [fipp "0.3.0"] ; fast idiomatic pretty printer

                 ;; end
                 ])
