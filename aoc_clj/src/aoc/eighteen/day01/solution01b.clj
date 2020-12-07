(ns aoc.eighteen.day01.solution01b)

(def content
  (map #(Integer/parseInt %)
       (clojure.string/split-lines
        (slurp "src/aoc/eighteen/day01/data.txt"))))

(defn sum-up-to [n]
  (reduce + (take n (cycle content))))

(loop [cnt 1
       coll #{}]
  (let [next-num (sum-up-to cnt)]
    (if (not (contains? coll next-num))
      (recur (inc cnt)
             (conj coll next-num))
      next-num)))
