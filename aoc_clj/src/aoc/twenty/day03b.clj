(ns aoc.twenty.day03b)

(def terrain
  (map #(clojure.string/split
         %
         #"")
       (clojure.string/split-lines
        (slurp "src/aoc/twenty/day03a-data.txt"))))

(defn nth-with-mod [coll pos]
  (if (>= pos (count coll))
    (nth coll (mod  pos (count coll)))
    (nth coll pos)))

(defn tobaggoing-down [slope point]
  [(+ (first slope) (first point))
   (+ (second slope) (second point))])

(defn traveled-position [slope start]
  (let [line-count (count terrain)]
    (take (/ line-count (first slope))
          (iterate (partial tobaggoing-down slope) start))))

(defn answer [slope start]
  (count
   (filter #(= "#" %)
           (for [k (traveled-position slope start)]
             (nth-with-mod
              (nth terrain (first k))
              (second k))))))

(*
 (answer [1 1] [0 0])
 (answer [1 3] [0 0])
 (answer [1 5] [0 0])
 (answer [1 7] [0 0])
 (answer [2 1] [0 0]))
