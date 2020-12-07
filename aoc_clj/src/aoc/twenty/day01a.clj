(ns aoc.twenty.day01a
  (:require [clojure.string :as str]))

(def f
  (->>
   (slurp "src/aoc/twenty/day01a-data.txt")
   clojure.string/split-lines
   (map #(Integer/parseInt %))))

(defn answer []
  (first
   (for [x f
         y f
         :when (= (+ x y) 2020)]
     (* x y))))

(answer)