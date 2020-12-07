(ns aoc.twenty.day02b)

(def f
  (->>
   (slurp "src/aoc/twenty/day01a-data.txt")
   clojure.string/split-lines
   (map #(Integer/parseInt %))))

(defn answer []
  (first
   (for [x f
         y f
         z f
         :when (= (+ x y z) 2020)]
     (* x y z))))

(answer)