(ns commons.clojure.strint
  (:require [clojure.core :only [slurp]]))

(defn- silent-read
  [s]
  (try
    (let [r (-> s java.io.StringReader. java.io.PushbackReader.)]
      [(read r) (slurp r)])
    (catch Exception e))) ; indicates an invalid form - s is just string data

(defn- interpolate
  ([s atom?]
    (lazy-seq
      (if-let [[form rest] (silent-read (subs s (if atom? 2 1)))]
        (cons form (interpolate (if atom? (subs rest 1) rest)))
        (cons (subs s 0 2) (interpolate (subs s 2))))))
  ([#^String s]
    (let [start (max (.indexOf s "~{") (.indexOf s "~("))]
      (if (== start -1)
        [s]
        (lazy-seq (cons
                    (subs s 0 start)
                    (interpolate (subs s start) (= \{ (.charAt s (inc start))))))))))

(defmacro <<
  [string]
  `(str ~@(interpolate string)))

(defmacro >>
  [string]
  `(str ~@(interpolate string)))


(def world "Rob")


;; Generic responses ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; These are easiest to establish using a rand-nth
;; There are, of course, situations where only a certain type of response is appropriate

;; Fun with Randomly-Generated Sentences  The computer makes sentences for you.
;; 5 Basic Sentence Patterns
;; Subject + Verb
;; I swim. Joe swims. They swam.
;; Subject + Verb + Object
;; I drive a car. Joe plays the guitar. They ate dinner.
;; Subject + Verb + Complement
;; I am busy. Joe became a doctor. They look sick.
;; Subject + Verb + Indirect Object + Direct Object
;; I gave her a gift. She teaches us English.
;; Subject + Verb + Object + Complement
;; I left the door open. We elected him president. They named her Jane.

;; Tenses

;; Present Continuous
;; I am swimming. Joe is sleeping. They are jogging.

;; Present Simple
;; I play tennis. He swims every day. I usually swim for two hours.

;; Present Perfect
;; I have eaten. He has just come home. They've already gone.

;; Past Simple
;; I rested. He played tennis yesterday. They drove to Boston.

;; Past Continuous
;; I was sleeping. She was cooking a while ago. They were talking.

;; Past Perfect
;; I had already seen it. He had played tennis.

;; Other

;; Possession
;; I have a camera. He owns a car. This house belongs to them.

;; Location
;; I am here. He swims in the river. They live in the mountains.

;; It's *fun* to swim. (Using adjectives similar to fun.)
;; It isn't healthy to smoke. Smoking is dangerous.

;; They *agreed* to swim. (Using verbs similar to agree.)
;; He didn't desire to work. They like to play.

;; They *asked* him to swim. (Using verbs similar to ask.)
;; He didn't advise me to work. They often encourage me to work harder.

;; They *enjoy* swimming. (Using verbs similar to enjoy.)
;; He didn't advise me to work. They often encourage me to work harder.



;; User-centric
;; Respond in a timely manner. Don't leave the host guessing as to whether you are going to attend. The host may have lots to prepare for! Always RSVP by the date on the invite if not sooner. If "regrets only" is asked for be sure to reply so your presence is not expected.
;; Don't take the easy way out. Not responding to the invite and making up an excuse later on will not be well received.
;; Thank the host for the invite. Express how flattering it is to be included in the celebration.
;; Schedule another time in the near future to catch up with the host in person if he/she is a close friend or relative.
;; Keep your response simple and short. Tell the host you are sorry you will not be able to make it this time, but hope they enjoy the party.
;; Send a card or a small gift. If the event is to celebrate a birthday, new baby, graduation or the like consider sending a card with a thoughtful note to show you are thinking about them even though you will not be there. You could send a gift too but keep it small and personal.
;; Ask the host a few days after the event how it went. Who doesn't like to talk about the great party they just threw?

(defn disbelief [])

;; two-tuples of expressing surprise and responses
(def express-surprise
  {"Wow! What a surprise!" "Yeah!"
   "That’s a surprise!" "It is"
   "That’s very surprising!" "Yup!"
   "Really?" "Positively!"
   "What?" "It’s true"
   "Are you serious? You must be joking" "I’m serious"
   "You’re kidding!" "No, I’m not"
   "Fancy that!" "It is"
   "I must say it surprises me" "Does it?"})

(def share-surprising-fact [
  "Do you know what? <pauze>"
  "Believe it or not, but <>"
  "You may not believe it, but <>"
  "Can you believe this?"])

(defn excuse
  "These are just lame excuses."
   [] (rand-nth ["Oops." "I’m sorry, my glasses are on back order."
                 "Hehe, my bad!" "Whoopsie." "I'm as surprised as you are."
                 "Yeah, that wasn't supposed to happen."]))

; Human to human interaction is a screenplay of symbolic acts, good friends can
; often handle a 'obviously I'm too lazy/occupied' (not so) funny lame-excuse
(defn lame-refusal
  "Use these to funnily yet lamely refuse to do something."
  [] (rand-nth [
      "My brother-in-law's friend's father's grandmother's sister's aunt's turtle died, and yes, it was a tragic death. I simply can not go into the details!"
      "My fortune teller advised against it."
      "I have a court hearing at the same because I stole the last cookie. What a coincidence?!"
      "I am writing a love letter to my ________ (insert your crush)"
      "I promised to help Ashley clean the toilet at the same time. She doesn't like doing it alone; she gets nervous that she will fall in! Some kind of toilet phobia(a weird name I can't remember for some reason...)"
      "I need to spend some serious time worrying about a good excuse as to why I don't want to do anything tonight. :)."
      "I do not usually go out on days that end with the letters 'day'. Sorry, personal preference."
      "People are blaming me for World War III, and I am trying to be a peacemaker."
      "Unfortunately there is a disturbance in the force, and it is not with me right now. I never go anywhere without the force, Skywalker strongly advises against it."
      "My plot to take over the presidency of the book club is thickening, and I must stay home to make sure everything is working out smoothly."
      "Obama is coming over tonight for some tea and crumpets. At least that is what his text stated. And like they say, never ditch the President, he likes his tea time!"
      "My hamster needs a good washing! He was playing with the pigs in the mud today. Stupid hamster!"
      "I am teaching my parrot to sing Adele's hit song 'Someone Like You'. Why? Because it is a catchy song! Sheesh, no need to judge me!"
      "I need to double check all of the expiration dates on my milk. You can never be too sure. And I love my Cheerios in the morning!"
      "Sorry, but I can't do anything for the next few hours. I am allowing my food to digest. You can never play it too safe!"
      "I am planning on going downtown to try out the new Wendys. I hear they can make a mean burger! Did you know they had just opened up?!"
      "My comfort zone and I are sipping our coffee and enjoying some bonding time. I don't like to leave my comfort zone."
      "I left my body in my other clothes, and those clothes are currently in the washer. (At least I wash my laundry unlike the Wimbledons!)"
      "My socks are matching! This is an natural disaster, an emergency!"
      "The ceiling tiles in my living room need to be recounted. Susan and I were having a debate on the count last night, and I must prove to her that I am right!"
      "I must eat more white dots; the monsters have not turned blue yet."
      "I am being deported Friday night, sorry I will not be able to make it. Oh, the boring and lifeless event is Thursday night? I am getting deported Thursday night, that is what I meant to say. Sorry, the wine must be getting to me."
      "I have lost my lucky rat's tail. Sorry, but I never go out without it!"
      "I must get to the bottom of this Cracker Jacks box so I can play with the toy. I heard it is a slinky! I love slinkies!"
      "I have to go to the post office to see if I am still wanted."
      "I am trying to be less popular. Someone has got to do it!"
      "I am currently working on my bucket list. And unfortunately, attending the book review party on Dr. Suess's latest book is not on my list."
      "My cat Mr. Spinkles just fell up the stairs. Can you believe it? Up the stairs?? 'Mr Spinkles, I am coming, just gimme one minu......'"
      "I need to study for my upcoming history test. You didn't know I was taking summer school this year? I didn't either, just started today actually! :)"
      "I am observing National 'Don't Go Out At All Week'. You haven't heard of that yet? Really, it is getting to be very popular in Amsterdam!"
      "I need to plant my watermelon seeds. Yes, I know it is the middle of the winter. Duh! I am starting ahead of the game this year!"
      "I changed my lock on the door and can't get out. I hate it when that happens!"
      "I made an appointment with my eyebrow specialist."
      "I have not met my daily Thesaurus quota yet. I look up thirty words every day."
                ]))

;; And remember, the key to a successful apology is sincerity!
;; If you don’t really mean it, then don’t say it.
(defn apologize []
  (rand-nth ["I'm sorry" "I apologize." "Please forgive me."
             "I sincerely apologize." "I was wrong."
             "I shouldn’t have said that."
             "My comments to you were ill-advised."
             "I made a stupid mistake." "I’m terribly sorry."
             "I’m genuinely sorry." "It was not my intention."
             "There is no excuse for my behaviour."]))


(<< "Hello ~(clojure.string/reverse world)")

;; Something (keywords/terms) in natural language needs to trigger
;; AI responses (contextually aware, also of co-word usage?)

;; No! My name is not boR! No, my name isn't that. I'm not called like that.
;; My name is actually ... ;; name not my *I* *my* not *our*
;; I R not n0m liek that :: fuzzy match name-n0m liek-like R-are/am
(>> "My name is Rob, not boR!")

(>> "~(excuse)")


(<< "You have approximately ~(.intValue 5.5) minutes left.")

(clojure.repl/doc meta)

(println "test")