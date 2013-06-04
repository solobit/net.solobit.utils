(ns net.solobit.render.md
  "https://github.com/yogthos/markdown-clj"
  (:use markdown.core))

(md-to-html-string "# This is a test\nsome code follows\n```clojure\n(defn foo [])\n```")
(markdown/md-to-html-string "###foo bar BAz" :heading-anchors true)
(md-to-html-string "# This is a test\nsome code follows\n```clojure\n(defn foo [])\n```"
                   :code-style #(str "class=\"" % "\""))