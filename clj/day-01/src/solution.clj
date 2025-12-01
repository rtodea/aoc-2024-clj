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
  
  ;; Test dial movement - FIXED VERSION
  (move-dial 0 \L 5 100)     ; From 0, move left 5: 99,98,97,96,95 - no zeros hit
  (move-dial 5 \L 10 100)    ; From 5, move left 10: 4,3,2,1,0,99,98,97,96,95 - hit 0 once
  (move-dial 95 \R 10 100)   ; From 95, move right 10: 96,97,98,99,0,1,2,3,4,5 - hit 0 once
  
  ;; Test edge cases
  (move-dial 0 \L 1 100)     ; From 0, left 1 step: should go to 99, no zeros hit
  (move-dial 1 \L 1 100)     ; From 1, left 1 step: should go to 0, hit 0 once
  (move-dial 99 \R 1 100)    ; From 99, right 1 step: should go to 0, hit 0 once
  
  ;; Test with sample instructions
  (count-dial-zero-is-hit test-instructions)
  )

(defn parse-instruction
  "Parses a single instruction like 'L68' or 'R48' into direction and steps."
  [instruction]
  (let [direction (first instruction)
        steps (Integer/parseInt (subs instruction 1))]
    {:direction direction :steps steps}))

(defn move-dial
  "Moves the dial position by the given steps in the given direction.
   Returns [new-position zero-hits] where zero-hits is how many times we passed 0."
  [current-pos direction steps dial-size]
  (let [step-fn (if (= direction \R) inc dec)]
    (loop [pos current-pos
           remaining-steps steps
           zero-hits 0]
      (if (zero? remaining-steps)
        [pos zero-hits]
        (let [new-pos (mod (step-fn pos) dial-size)
              new-zero-hits (if (zero? new-pos) (inc zero-hits) zero-hits)]
          (recur new-pos
                 (dec remaining-steps)
                 new-zero-hits))))))

(defn count-dial-zero-is-hit
    "Counts how many times the dial hits zero."
    [instructions]
    (let [dial-size 100  ; 0-99 dial
          initial-pos 0]
      (loop [position initial-pos
             remaining-instructions instructions
             total-zero-hits 0]
        (if (empty? remaining-instructions)
          total-zero-hits
          (let [instruction (first remaining-instructions)
                {:keys [direction steps]} (parse-instruction instruction)
                [new-pos zero-hits] (move-dial position direction steps dial-size)]
            (recur new-pos
                   (rest remaining-instructions)
                   (+ total-zero-hits zero-hits)))))))

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