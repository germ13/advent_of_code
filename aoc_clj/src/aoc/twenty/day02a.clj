(ns aoc.twenty.day02a)

(def a1 "4-5 j: jhjjhxhjkxj")
(def a2 "16-17 k: kkkkkkkzkkkkrkkmfkk")

(defn parser [expr]
  (let [tokens (clojure.string/split expr #" ")]
    {:bounds (first tokens)
     :search-character (first (second tokens))
     :sentence (last tokens)}))

(defn my-count [character sentence]
  (count (filter #(= character %) sentence)))

(defn my-range [bounds]
  (->>
   (clojure.string/split bounds #"-")
   (map #(Integer/parseInt %))))

(defn xor [a b]
  (or (and a (not b))
      (and (not a) b)))

(defn password-check [expr]
  (let [p (parser expr)
        bounds (my-range (:bounds p))
        min-bound (first bounds)
        max-bound (second bounds)
        search-character (:search-character p)
        sentence (:sentence p)
        actual-count (my-count search-character sentence)]
    (and (>= actual-count min-bound)
         (<= actual-count max-bound))))

(defn password-check2 [expr]
  (let [p (parser expr)
        bounds (my-range (:bounds p))
        first-index (first bounds)
        second-index (second bounds)
        search-character (:search-character p)
        sentence (:sentence p)]
    (xor (=  (nth sentence (dec first-index) ) search-character )
         (=  (nth sentence (dec second-index)) search-character))))

(defn answer1 []
  (->>
   (slurp "src/aoc/twenty/day02a-data.txt")
   clojure.string/split-lines
   (map #(password-check %))
   (filter true?)
   count))

(defn answer2 []
  (->>
   (slurp "src/aoc/twenty/day02a-data.txt")
   clojure.string/split-lines
   (map #(password-check %))
   (filter true?)
   count))





