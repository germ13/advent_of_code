(ns aoc.eighteen.day01.solution01a)

(defn answer []
  (->>
   (slurp "src/aoc/eighteen/day01/data.txt")
   clojure.string/split-lines
   (map #(Integer/parseInt %))
   (reduce +)))

(answer)
