(ns aoc.eighteen.day03.solution
  (:require [clojure.string :as str]))

(def content
  (->>
   (slurp "src/aoc/eighteen/day03/data.txt")
   (str/split-lines)))

(def testdata (first content))

(defn parse-line-data [line]
  (let
   [claim (str/trim (first (clojure.string/split testdata #"@")))
    claim-rest (second (clojure.string/split testdata #"@"))
    position (str/split (first (str/split claim-rest  #":")), #",")
    xpos (Integer/parseInt (str/trim (first position)))
    ypos (Integer/parseInt (second position))
    dimension (str/split (second (str/split (second (str/split claim-rest  #",")), #":")), #"x")
    width (Integer/parseInt (str/trim (first dimension)))
    height (Integer/parseInt (str/trim (second dimension)))]
    [claim xpos ypos width height]))