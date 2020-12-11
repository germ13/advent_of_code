(ns aoc.twenty.day05a)

(def file "src/aoc/twenty/day05.txt")

(defn seats [filename]
  (->>
   (slurp filename)
   (clojure.string/split-lines)))

(defn take-half [direction number-range]
  (let [start (first number-range)
        end (second number-range)
        half (int (/ (- end start) 2))]
    (cond
      (or (= \F direction)
          (= \L direction))
      [start (+ start half)]
      (or (= \B direction)
          (= \R direction))
      [(+ start half 1) end]
      :else
      [start end])))

(defn find-row [expr divisions]
  (loop [s (subs expr 0 7)
         r divisions]
    (if (empty? s)
      (first r)
      (recur (rest s)
             (take-half (first s) r)))))

(defn find-column [expr divisions]
  (loop [s (subs expr 7)
         r divisions]
    (if (empty? s)
      (first r)
      (recur (rest s)
             (take-half (first s) r)))))

(defn find-seat [expr]
  [(find-row expr [0 127]) (find-column expr [0 7])])

(defn all-seats [file]
  (map find-seat (seats file)))

(defn get-seat-ids [file]
  (map #(+ (* (first %) 8) (second %)) (map find-seat (seats file))))

(defn answer [file]
   (apply max (map #(+ (* (first %) 8) (second %)) (map find-seat (seats file)))))

(defn answer2 [file]
  (let [maxmax (apply max (map #(+ (* (first %) 8) (second %)) (map find-seat (seats file))))
        minmin (apply min (map #(+ (* (first %) 8) (second %)) (map find-seat (seats file))))]
    (print (range minmin (inc maxmax)))
    (clojure.set/difference
     (set (range minmin (inc maxmax)))
     (set (get-seat-ids file)))))