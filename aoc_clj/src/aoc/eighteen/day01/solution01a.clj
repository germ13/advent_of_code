(ns aoc.eighteen.day01.solution01)

(defn answer []
  (->>
   (slurp "src/aoc/eighteen/day01/data.txt")
   clojure.string/split-lines
   (map #(Integer/parseInt %))
   (reduce +)))

(answer)
