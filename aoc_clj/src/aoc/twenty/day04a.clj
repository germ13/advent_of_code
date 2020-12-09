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

get-passport-properties

(defn check-properties2 [expr]
  (let [k
        (and
         (and (:byr expr)
              (every? #(Character/isDigit %) (:byr expr))
              (>= 1920 (Integer/parseInt (:byr expr)))
              (<= 2002 (Integer/parseInt (:byr expr))))
         (and (:iyr expr)
              (every? #(Character/isDigit %) (:iyr expr))
              (>= 2010 (Integer/parseInt (:iyr expr)))
              (<= 2020 (Integer/parseInt (:iyr expr))))
         (and (:eyr expr)
              (every? #(Character/isDigit %) (:eyr expr))
              (>= 2020 (Integer/parseInt (:eyr expr)))
              (<= 2030 (Integer/parseInt (:eyr expr))))
         (and (:hgt expr)
              (cond
                (clojure.string/ends-with? (:hgt expr) "cm")
                (and (every? #(Character/isDigit %) (subs (:hgt expr) 0 3))
                     (>= 150 (Integer/parseInt (subs (:hgt expr) 0 3)))
                     (<= 193 (Integer/parseInt (subs (:hgt expr) 0 3)))
                     (= (count (:hgt expr)) 5))
                (clojure.string/ends-with? (:hgt expr) "in")
                (and  (every? #(Character/isDigit %) (subs (:hgt expr) 0 2))
                      (>= 59 (Integer/parseInt (subs (:hgt expr) 0 2)))
                      (<= 76 (Integer/parseInt (subs (:hgt expr) 0 2)))
                      (= (count (:hgt expr)) 4))
                :else false))
         (and (:hcl expr)
              (some? (re-matches #"^#([a-z0-9]){6}$" (:hcl expr))))
         (and (:pid expr)
              (some? (re-matches #"^([0-9]){9}$" (:pid expr))))

         (and (:ecl expr)
              (or
               (= (:ecl expr) "amb")
               (= (:ecl expr) "blu")
               (= (:ecl expr) "brn")
               (= (:ecl expr) "gry")
               (= (:ecl expr) "hzl")
               (= (:ecl expr) "oth"))))]
    (println [expr k])
    k
    ))

;good
(def good {:iyr "2010", :hgt "158cm", :hcl "#b6652a", :ecl "blu", :byr "1944", :eyr "2021", :pid "1093154719", :good "good"})

;bad
(def good {:hgt "59cm", :ecl "zzz", :eyr "2038", :hcl "74454a", :iyr "2023", :pid "3556412378", :byr "2007"})

(:ecl good)

         (and (:ecl good)
              (or
               (= (:ecl good) "amb")
               (= (:ecl good) "blu")
               (= (:ecl good) "brn")
               (= (:ecl good) "gry")
               (= (:ecl good) "hzl")
               (= (:ecl good) "oth")))

(and (:pid good) nil)

(and (:pid good)
     (re-matches #"([0-9]){9}" (:pid good)))

(and true ["093154719" "9"])


(defn check-properties [expr]
  (and
   (:ecl expr)
   (:pid expr)
   (:eyr expr)
   (:hcl expr)
   (:byr expr)
   (:iyr expr)
   (:hgt expr)))

(defn answer []
  (count
   (filter #(not (nil? %))
           (map #(check-properties2 %)  get-passport-properties))))


(count get-passport-properties)
(answer)
