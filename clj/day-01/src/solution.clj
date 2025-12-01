(ns solution
  (:require [clojure.string :as str])
  (:import [java.io File]))

(defn get-current-dir []
  (System/getProperty "user.dir"))

(defn file-exists? [filename]
  (.exists (File. filename)))

(defn parse-instructions-from-file
  "Parses the instructions from the given file."
  [filename]
  (->> (slurp filename)
       str/split-lines)) ; tap into parsed lines 

(comment
  (get-current-dir)
  (file-exists? "./input.txt")
  
  ;; Test with sample data first
  (def test-instructions ["L68" "L30" "R48" "L5" "R60" "L55" "L1" "L99" "R14" "L82"])
  
  ;; Test individual functions
  (parse-instruction "L68")  ; Should return {:direction \L, :steps 68}
  (parse-instruction "R48")  ; Should return {:direction \R, :steps 48}
  
  ;; Test dial movement
  (calculate-next-pos 50 \L 68 100)  ; Should be 82
  (calculate-next-pos 82 \L 30 100)  ; Should be 52
  (calculate-next-pos 52 \R 48 100)  ; Should be 0
  
  ;; Test with sample instructions
  (count-dial-zero-is-hit test-instructions)
  )

(defn parse-instruction
  "Parses a single instruction like 'L68' or 'R48' into direction and steps."
  [instruction]
  (let [direction (first instruction)
        steps (Integer/parseInt (subs instruction 1))]
    {:direction direction :steps steps}))

(defn calculate-next-pos
  "Calculates the new dial position after rotating."
  [current-pos direction steps dial-size]
  (let [step-val (if (= direction \R) steps (- steps))]
    (mod (+ current-pos step-val) dial-size)))

(defn count-dial-zero-is-hit
  "Counts how many times the dial is left pointing at 0 after any rotation."
  [instructions]
  (let [dial-size 100
        initial-pos 50] ;; Starts at 50
    (second (reduce (fn [[pos total-hits] instruction]
                      (let [{:keys [direction steps]} (parse-instruction instruction)
                            new-pos (calculate-next-pos pos direction steps dial-size)]
                        [new-pos (if (zero? new-pos) (inc total-hits) total-hits)]))
                    [initial-pos 0]
                    instructions))))

;; Main solution using input.txt file
(comment
  ;; Check file setup
  (get-current-dir)
  (file-exists? "./input.txt")
  (file-exists? "input.txt")
  
  ;; Direct pipeline: file -> parse -> count zero hits
  (->> "./input.txt"
       parse-instructions-from-file
       count-dial-zero-is-hit)
  
  ;; Alternative file path if needed
  (->> "input.txt"
       parse-instructions-from-file
       count-dial-zero-is-hit)
  
  ;; Debug version to see intermediate steps
  (->> "./input.txt"
       parse-instructions-from-file
       (#(do (println "Instructions count:" (count %))
             (println "First 5:" (take 5 %))
             %))
       count-dial-zero-is-hit)
  )