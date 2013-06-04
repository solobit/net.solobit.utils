
(def alphabet (map char (range (int \A) (inc (int \Z)))))
(def rot13 (take 26 (drop 13 (cycle alphabet))))
(def rot13pairs (map (fn [a b] [a b]) alphabet rot13))
(into #{} rot13pairs)
(def rot13-cipher (zipmap alphabet rot13))

(defn rotate [c n]
  (take (count c)
        (drop n (cycle c))))

(defn rot13-one-char [c]
  (if (contains?
       rot13-cipher c) (rot13-cipher c)
    c))

(defn rot13 [string]
  (apply str (map rot13-one-char string)))

(def secret-message
  "FCMJ C CM U JLIALUGGCHA MSMNYG ZIL NBY CVG 704 ZIL WIGJONCHA QCNB
MSGVIFCW YRJLYMMCIHM. CN BUM VYYH OMYX ZIL MSGVIFCW WUFWOFUNCIHM CH
XCZZYLYHNCUF UHX CHNYALUF WUFWOFOM, YFYWNLCW WCLWOCN NBYILS,
GUNBYGUNCWUF FIACW, UHX ULNCZCWCUF CHNYFFCAYHWY.")

(defn count-letters [string]
  (frequencies string))

(defn count-letters0 [string]
  (reduce
    (fn [result item] (assoc result item (+ 1 (get result item 0))))
     {} string))


(defn count-letters-fnil [string]
  (reduce
    (fn [result item] (update-in result [item] (fnil #(+ 1 %) 0)))
     {} string))

(def common-letters (sort-by second (count-letters-fnil secret-message)))

(def other (rotate alphabet 6))

(def other-pairs (conj (map (fn [a b] [a b]) alphabet other) [\space \space]))

(def other-cipher (zipmap alphabet other))

(defn other-cipher-one-char [c]
  (if (contains? other-cipher c) (other-cipher c)))

(defn other [string]
  (apply str (map other-cipher-one-char string)))

common-letters

(other "CAN U PLS STFU")
