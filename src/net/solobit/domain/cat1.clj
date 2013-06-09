

(ns net.solobit
  (:require [net.solobit.utils.encoding :refer [hexify-str]]))

(defn unicodes [rfrom rto]
  (doall (pmap #(char %) (range rfrom rto))))

(def iso-8859-1 (unicodes 0 256))


(map #(int %)
     '(\§ \© \ª \« \¬ \­ \® \¯ \° \± \º \¹ \² \³ \´ \µ \¶ \· \» \¼ \½ \¾
          \⯗ \Ʃ))

;(unicodes 99 2400)

(hexify-str "Φ")


'("previous page" \⎗ \⎙ \⎌ "UNDO SYMBOL")

(def directions '(\⭶ \⭷ \⭸ \⭹))

(def target '(\⮔ \⟴ \ⴲ \⸭))

(def cross-check '(\✓ \✔ \✕ \✖ \✗ \✘))

ns net.solobit
  (:require [net.solobit.utils.encoding :refer [hexify-str]]))

(defn unicodes [rfrom rto]
  (doall (pmap #(char %) (range rfrom rto))))

(def iso-8859-1 (unicodes 0 256))


(def quotes '(\❛ \❜ \❝ \❞ \❟ \❠ \〝 \〞 \〟))


(def brackets '(\❨ \❩ \❪ \❫ \❬ \❭ \❮ \❯ \❰ \❱ \❲ \❳ \❴ \❵
                \〈 \〉 \《 \》 \「 \」 \『 \』 \【 \】 \〒 \〓 \〔 \〕 \〖 \〗 \〘 \〙 \〚
                   \⸨ \⸩))

(def letters-gothic '(\ⴀ \ⴁ \ⴂ \ⴃ \ⴄ \ⴅ \ⴆ \ⴇ \ⴈ \ⴉ \ⴊ \ⴋ \ⴌ \ⴍ \ⴎ
                         \ⴏ \ⴐ \ⴑ \ⴒ \ⴓ \ⴔ \ⴕ \ⴖ \ⴗ \ⴘ \ⴙ \ⴚ \ⴛ \ⴜ
                         \ⴝ \ⴞ \ⴟ \ⴠ \ⴡ \ⴢ \ⴣ \ⴤ \ⴥ))

(doall (repeatedly 24 #(rand-nth letters-gothic)))

(def threads '(\⟅ \➰ \➿))
(def interface '(\⟛ \⟚ \⟜))

(def source-code '(\ⵌ \Ⲥ))

(def flanks '(\⨴ \⨵ \⨭ \⨮ \⫹ \⫺))

(def un-redo '(\⟲ \⟳))

(def numbers '(\❶ \❷ \❸ \❹ \❺ \❻ \❼ \❽ \❾ \❿
               \➀ \➁ \➂ \➃ \➄ \➅ \➆ \➇ \➈ \➉
               \➊ \➋ \➌ \➍ \➎ \➏ \➐ \➑ \➒ \➓ ))