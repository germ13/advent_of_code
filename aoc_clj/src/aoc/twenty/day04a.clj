(ns aoc.twenty.day04a)

(def passports
  (map #(clojure.string/split % #" ")
       (map #(clojure.string/replace % #"\n" " ")
            (clojure.string/split
             (slurp "src/aoc/twenty/day04.txt")
             #"\n\n"))))

(defn create-kvp [expr]
  (let [kvp
        (clojure.string/split expr #":")]
    {(keyword (first kvp)) (second kvp)}))

(def get-passport-properties
  (map #(apply merge %)
       (for [p passports]
         (map #(create-kvp %) p))))

(defn check-properties [expr]
   (and
   (:ecl expr)
   (:pid expr)
   (:eyr expr)
   (:hcl expr)
   (:byr expr)
   (:iyr expr)
   (:hgt expr)))

(defn anser []
  (count
   (filter #(not (nil? %))
           (map #(check-properties %)  get-passport-properties))))
