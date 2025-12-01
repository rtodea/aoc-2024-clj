(ns solution
  (:require [clojure.string :as str])
  (:import [java.io File]))

(defn parse-instruction
  "Parses a single instruction like 'L68' or 'R48' into direction and steps."
  [instruction]
  (let [direction (first instruction)
        steps (Integer/parseInt (subs instruction 1))]
    {:direction direction :steps steps}))

(defn parse-instructions-from-file
  "Parses the instructions from the given file."
  [filename]
  (->> (slurp filename)
       str/split-lines))

(defn count-zeros-in-rotation
  "Counts how many times 0 is hit during a rotation."
  [current-pos direction steps dial-size]
  (let [step-val (if (= direction \R) 1 -1)
        ;; Generate path of all positions visited (excluding start)
        path (map #(mod (+ current-pos (* % step-val)) dial-size)
                  (range 1 (inc steps)))]
    [(last path) (count (filter zero? path))]))

(defn count-dial-zero-is-hit
  "Counts how many times the dial hits 0 during any rotation."
  [instructions]
  (let [dial-size 100
        initial-pos 50] ;; Starts at 50
    (second (reduce (fn [[pos total-hits] instruction]
                      (let [{:keys [direction steps]} (parse-instruction instruction)
                            [new-pos hits] (count-zeros-in-rotation pos direction steps dial-size)]
                        [new-pos (+ total-hits hits)]))
                    [initial-pos 0]
                    instructions))))

(defn solve
  "Reads instructions from a file and counts how many times the dial hits zero."
  [filename]
  (->> filename
       parse-instructions-from-file
       count-dial-zero-is-hit))
