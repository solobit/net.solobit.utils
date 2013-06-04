(ns net.solobit.ai.sentimental.core
	(:import [snowball-stemmer])
	(:require [net.solobit.ai.sentimental.train :as tr])
	(:use [stemmer.snowball]
		  	[opennlp.nlp]
		  	[clojure.java.io]))


(def eng-stemmer (stemmer "english"))
(def tokenizer (make-tokenizer "src/net/solobit/ai/models/en-token.bin"))
(def detokenizer (make-detokenizer "src/net/solobit/ai/models/english-detokenizer.xml"))

; the actual categorizer
(def categorize (make-document-categorizer tr/senti-model))

(defn stop-words []
	(set (tr/get-lines "resources/ai/stop_words.txt")))

(defn strip-stop-words [l]
	(filter (fn [x] (not (contains? (stop-words) x)))
			(set l)))

(defn bag-of-words
	"Converts a string into a set of unique words/elements,
	each stemmed (in English)"
	[s]
	(set (map (fn [x] (eng-stemmer x))
			(strip-stop-words (tokenizer s)))))

(defn compact
	"Takes a string, strips out stop words, and stems each word.
	Returns a string"
	[s]
	(detokenizer (bag-of-words s)))

(categorize "Hey I want to die myself|!@")