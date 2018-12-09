(ns aoc.eighteen.day02.solution)

(def content
  (->>
   (slurp "src/aoc/eighteen/day02/data.txt")
   (clojure.string/split-lines)))

(defn has? [code amount]
  (if (> (count (filter (fn [[k v]] (= v amount)) (frequencies code))) 0)
    true
    false))

(defn repeat-count [test]
  (get
   (frequencies
    (map #(has? % test) content))
   true))

(* (repeat-count 2) (repeat-count 3))