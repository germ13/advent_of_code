(ns aoc.twenty.day04a)

(def file "src/aoc/twenty/day04.txt")

(defn passports [filename]
  (map #(clojure.string/split % #" ")
       (map #(clojure.string/replace % #"\n" " ")
            (clojure.string/split
             (slurp filename)
             #"\n\n"))))

(defn create-kvp [expr]
  (let [kvp
        (clojure.string/split expr #":")]
    {(keyword (first kvp)) (second kvp)}))

(defn get-passport-properties [filename]
  (map #(apply merge %)
       (for [p (passports filename)]
         (map #(create-kvp %) p))))

(defn regexer [pattern expr]
  (> (count (re-matches pattern expr)) 0))

(defn check-properties [expr]
  (and
   (:ecl expr)
   (:pid expr)
   (:eyr expr)
   (:hcl expr)
   (:byr expr)
   (:iyr expr)
   (:hgt expr)))

(defn check-properties2 [expr]
  (and
   (and (:byr expr)
        (every? #(Character/isDigit %) (:byr expr))
        (>= (Integer/parseInt (:byr expr)) 1920)
        (<= (Integer/parseInt (:byr expr)) 2002))
   (and (:iyr expr)
        (every? #(Character/isDigit %) (:iyr expr))
        (>= (Integer/parseInt (:iyr expr)) 2010)
        (<= (Integer/parseInt (:iyr expr)) 2020))
   (and (:eyr expr)
        (every? #(Character/isDigit %) (:eyr expr))
        (>= (Integer/parseInt (:eyr expr)) 2020)
        (<= (Integer/parseInt (:eyr expr)) 2030))
   (and (:hgt expr)
        (cond
          (clojure.string/ends-with? (:hgt expr) "cm")
          (and (every? #(Character/isDigit %) (subs (:hgt expr) 0 3))
               (>= (Integer/parseInt (subs (:hgt expr) 0 3)) 150)
               (<= (Integer/parseInt (subs (:hgt expr) 0 3)) 193)
               (= (count (:hgt expr)) 5))
          (clojure.string/ends-with? (:hgt expr) "in")
          (and  (every? #(Character/isDigit %) (subs (:hgt expr) 0 2))
                (>= (Integer/parseInt (subs (:hgt expr) 0 2)) 59)
                (<= (Integer/parseInt (subs (:hgt expr) 0 2)) 76)
                (= (count (:hgt expr)) 4))
          :else false))
   (and (:hcl expr)
        (regexer #"^#([a-z0-9]){6}$" (:hcl expr)))
   (and (:pid expr)
        (regexer #"^([0-9]){9}$" (:pid expr)))
   (and (:ecl expr)
        (or
         (= (:ecl expr) "amb")
         (= (:ecl expr) "blu")
         (= (:ecl expr) "brn")
         (= (:ecl expr) "gry")
         (= (:ecl expr) "grn")
         (= (:ecl expr) "hzl")
         (= (:ecl expr) "oth")))))

(defn answer [filename]
  (count
   (filter true?
           (map #(check-properties2 %)  (get-passport-properties filename)))))

(answer file)