(ns aoc.eighteen.day02.solution)

(def content
  (->>
   (slurp "src/aoc/eighteen/day02/data.txt")
   (clojure.string/split-lines)))

(defn like-elements [coll01 coll02]
  (for [i (range (count coll01))
        :when (= (nth coll01 i)
                 (nth coll02 i))]
    (nth coll02 i)))

(defn rator? [coll01 coll02]
  (if (= (dec (count coll01))
         (count (like-elements coll01 coll02)))
    true
    false))

(for [i content
      j content
      :when (rator? i j)]
  (like-elements i j))
