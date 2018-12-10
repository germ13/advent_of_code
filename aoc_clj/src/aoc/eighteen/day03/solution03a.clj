(ns aoc.eighteen.day03.solution
  (:require [clojure.string :as str]))

(def content
  (->>
   (slurp "src/aoc/eighteen/day03/data.txt")
   (str/split-lines)))

(def watch1 "#1 @ 551,185: 21x30")
(def watch2 "#3 @ 563,200: 29x27")

(defn parse-line-data [line]
  (let [claim
        (str/trim (first (clojure.string/split line #"@")))
        claim-rest
        (second (clojure.string/split line #"@"))
        position
        (str/split (first (str/split claim-rest  #":")), #",")
        xpos
        (Integer/parseInt (str/trim (first position)))
        ypos
        (Integer/parseInt (second position))
        dimension
        (str/split (second (str/split (second (str/split claim-rest  #",")), #":")), #"x")
        width
        (Integer/parseInt (str/trim (first dimension)))
        height
        (Integer/parseInt (str/trim (second dimension)))]
    {:id claim :x xpos :y ypos :w width :h height}))

(defn dimensions [rect]
  {:x_start (:x rect)
   :y_start (:y rect)
   :x_end (+ (:x rect) (:w rect))
   :y_end (+ (:y rect) (:h rect))})

(defn is-inside? [rect point]
  (if (and (<= (:x_start rect) (:x point))
           (<= (:x point) (:x_end rect))
           (<= (:y_start rect) (:y point))
           (<= (:y point) (:y_end rect)))
    true
    false))

(let
 [line1 (parse-line-data watch1)
  line2 (parse-line-data watch2)
  rect1 (dimensions line1)
  rect2 (dimensions line2)]
  (for [i (range (:x_start rect1) (:x_end rect1))
        j (range (:y_start rect1) (:y_end rect1))]
    [i j]))
