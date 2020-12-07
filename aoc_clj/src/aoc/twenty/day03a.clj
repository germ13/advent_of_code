(ns aoc.twenty.day03a)

(def terrain
  (map #(clojure.string/split
         %
         #"")
       (clojure.string/split-lines
        (slurp "src/aoc/twenty/day03a-data.txt"))))

(def start [0 0])
(def slope [1 3])

(defn nth-with-mod [coll pos]
  (if (>= pos (count coll))
    (nth coll (mod  pos (count coll)))
    (nth coll pos)))

(def line-count (count terrain))

(defn tobaggoing-down [point]
  [(+ (first slope) (first point))
   (+ (second slope) (second point))])

(def traveled-position
  (take line-count (iterate tobaggoing-down start)))

(defn answer []
  (count
   (filter #(= "#" %)
           (for [k traveled-position]
             (nth-with-mod
              (nth terrain (first k))
              (second k))))))

(answer)