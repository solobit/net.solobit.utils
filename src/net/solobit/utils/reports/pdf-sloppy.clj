(ns net.solobit.utils.reports
  "https://github.com/yogthos/clj-pdf
  Distributed under LGPL, the same as iText version 0.4.2 and JFreeChart on which this library depends on."
  (:use clj-pdf.core)
  (:import [java.awt.Image]
           [java.net.URL]
           [javax.imageio]))

(pdf
  [{:title  "Test doc"
    :left-margin   10
    :right-margin  10
    :top-margin    20
    :bottom-margin 25
    :subject "Some subject"
    :size          :a4
    :orientation   :landscape
    :author "John Doe"
    :creator "Jane Doe"
    :font  {:size 11} ;specifies default font
    :doc-header ["inspired by" "William Shakespeare"]
    :header "Page header text appears on each page"
    :letterhead ["A simple Letter head"] ;Sequence of any elements. If set, the first page shows letterhead instead of header
    :footer {:text "Page footer text appears on each page (includes page number)"
             :align :left ;optional footer alignment of :left|:right|:center defaults to :right
             :footer-separator "text which will be displayed between current page number and total pages, defaults to /"
             :start-page 2 ;optional parameter to indicate on what page the footer starts, has no effect when :pages is set to false
             }

    :pages true ;specifies if total pages should be printed in the footer of each page
    }
   [:list {:roman true} [:chunk {:style :bold} "a bold item"] "another item" "yet another item"]
   [:phrase "some text"]
   "this text will be treated as a paragraph"
   [:subscript "some subscript text"]
   [:subscript {:style :bold} "some bold subscript text"]
   [:chapter "First Chapter"]
   [:chapter [:paragraph "Second Chapter"]]
   [:phrase "some text here"]
   [:phrase {:style :bold :size 18 :family :halvetica :color [0 255 221]} "Hello Clojure!"]
   [:phrase [:chunk {:style :italic} "chunk one"] [:chunk {:size 20} "Big text"] "some other text"]
   [:anchor {:target "http://google.com"} "google"]
   [:anchor {:style {:size 15} :leading 20 :id "targetAnchor"} "some anchor"]
   [:anchor {:target "#targetAnchor"} "this anchor points to some anchor"]
   [:anchor [:phrase {:style :bold} "some anchor phrase"]]
   [:anchor "plain anchor"]
   [:cell {:colspan 2} "Foo"]
   [:cell {:colspan 3 :rowspan 2} "Foo"]
   [:cell [:phrase {:style :italic :size 18 :family :halvetica :color [200 55 221]} "Hello Clojure!"]]
   [:cell {:color [100 10 200]} "bar1"]
   [:cell [:table ["Inner table Col1" "Inner table Col2" "Inner table Col3"]]]
   [:superscript "some superscript text"]
   [:superscript {:style :bold} "some bold superscript text"]
   [:line]
   [:pdf-table
    {:bounding-box [50 100]
     :horizontal-align :right
     :spacing-before 100}
    [10 20 15]
    ["foo" [:chunk {:style :bold} "bar"] [:phrase "baz"]]
    [[:pdf-cell "foo"] [:pdf-cell "foo"] [:pdf-cell "foo"]]
    [[:pdf-cell "foo"] [:pdf-cell "foo"] [:pdf-cell "foo"]]]
   [:paragraph "a fine paragraph"]
   [:paragraph {:keep-together true :indent 20} "a fine paragraph"]
   [:paragraph
    {:style :bold :size 10 :family :halvetica :color [0 255 221]}
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit."]
   ;;font set in the paragraph can be modified by its children
   [:paragraph {:indent 50 :color [0 255 221]} [:phrase {:style :bold :size 18 :family :halvetica} "Hello Clojure!"]]
   [:paragraph "256" [:chunk {:super true} "5"] " or 128" [:chunk {:sub true} "2"]]
   [:line {:dotted true}]
   [:line {:dotted true :gap 10}]
   [:chunk {:style :bold} "small chunk of text"]
   [:chunk {:super true} "5"]
   [:spacer ] ;creates 1 new lines
   [:spacer 5] ;creates 5 new lines
   [:pagebreak]
   [:chart {:type :line-chart
         :time-series true
         :time-format "MM/yy"
         :title "Time Chart"
         :x-label "time"
         :y-label "progress"}
    ["Occurances" ["01/11" 200] ["02/12" 400] ["05/12" 350] ["11/13" 600]]]
   [:chart {:type :pie-chart :title "Big Pie"} ["One" 21] ["Two" 23] ["Three" 345]]
   [:chart,
    {:x-label "time"
     :y-label "progress"
     :time-series true
     :title   "Time Chart"
     :type    :line-chart}
    ["Incidents"
     ["2011-01-03-11:20:11" 200]
     ["2011-02-11-22:25:01" 400]
     ["2011-04-02-09:35:10" 350]
     ["2011-07-06-12:20:07" 600]]]
   [:chart {:type :line-chart :title "Line Chart" :x-label "checkpoints" :y-label "units"}
    ["Foo" [1 10] [2 13] [3 120] [4 455] [5 300] [6 600]]
    ["Bar" [1 13] [2 33] [3 320] [4 155] [5 200] [6 300]]]
   [:pagebreak]
    ;;    [:image
    ;;    {:xscale     0.5
    ;;     :yscale     0.8
    ;;     :align      :center
    ;;     :annotation ["FOO" "BAR"]
    ;;     :pad-left   100
    ;;     :pad-right  50}
    ;;    (javax.imageio.ImageIO/read "mandelbrot.jpg")]
   ;[:image "test/mandelbrot.jpg"]
   [:image "http://clojure.org/space/showimage/clojure-icon.gif"]
   [:chunk {:sub true} "2"]
   [:list {:roman true} [:chunk {:style :bold} "a bold item"] "another item" "yet another item"]
   [:list {:symbol "*"} [:chunk {:style :bold} "a bold item"] "another item" "yet another item"]
   [:heading "Lorem Ipsum"]
   [:chart {:type "bar-chart" :title "Bar Chart" :x-label "Items" :y-label "Quality"}
    [2 "Foo"] [4 "Bar"] [10 "Baz"]]
   [:heading {:style {:size 15}} "Lorem Ipsum"]
   [:heading {:style {:size 10 :color [100 40 150] :align :right}} "Foo"]
   [:phrase "some more text"]
   [:chapter [:paragraph {:color [250 0 0]} "Chapter"]
   [:section "Section Title" "Some content"]
   [:section [:paragraph {:size 10} "Section Title"]
    [:paragraph "Some content"]
    [:paragraph "Some more content"]
    [:section {:color [100 200 50]} [:paragraph "Nested Section Title"]
              [:paragraph "nested section content"]]]]
   [:table {:header ["Row 1" "Row 2" "Row 3"] :width 50 :border false :cell-border false}
    [[:cell {:colspan 2} "Foo"] "Bar"]
    ["foo1" "bar1" "baz1"]
    ["foo2" "bar2" "baz2"]]

   [:table {:border-width 10 :header ["Row 1" "Row 2" "Row 3"]}
    ["foo" "bar" "baz"]
    ["foo1" "bar1" "baz1"]
    ["foo2" "bar2" "baz2"]]

   [:table {:border false
            :widths [2 1 1] ; the widths will be: a width of 50% for the first column, 25% for the second and third column.
            :header [{:color [100 100 100]} "Singe Header"]}
    ["foo" "bar" "baz"]
    ["foo1" "bar1" "baz1"]
    ["foo2" "bar2" "baz2"]]

   [:table {:cell-border false :header [{:color [100 100 100]} "Row 1" "Row 2" "Row 3"] :cellSpacing 20 :header-color [100 100 100]}
    ["foo"
     [:cell [:phrase {:style :italic :size 18 :family :halvetica :color [200 55 221]} "Hello Clojure!"]]
     "baz"]
    ["foo1" [:cell {:color [100 10 200]} "bar1"] "baz1"]
    ["foo2" "bar2" "baz2"]]
   [:paragraph "yet more text"]]


  "test/sloppydoc.pdf")

(def employees
  [{:country "Germany",
    :place "Nuremberg",
    :occupation "Engineer",
    :name "Neil Chetty"}
   {:country "Germany",
    :place "Ulm",
    :occupation "Engineer",
    :name "Vera Ellison"}])

(def employee-template
  (template
    [:paragraph
     [:heading $name]
     [:chunk {:style :bold} "occupation: "] $occupation "\n"
     [:chunk {:style :bold} "place: "] $place "\n"
     [:chunk {:style :bold} "country: "] $country
     [:spacer]]))



;; Post processing
(def employee-template-paragraph
  (template
    [:paragraph
     [:heading (if (and $name (.startsWith $name "Alfred"))
                 (.toUpperCase $name) $name)]
     [:chunk {:style :bold} "occupation: "] $occupation "\n"
     [:chunk {:style :bold} "place: "] $place "\n"
     [:chunk {:style :bold} "country: "] $country
     [:spacer]]))