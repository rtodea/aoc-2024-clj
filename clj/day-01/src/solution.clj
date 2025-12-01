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
  (move-dial 0 \L 5 100)     ; Move left 5 steps from 0: should go to 95, hit 0 once
  (move-dial 95 \R 10 100)   ; Move right 10 steps from 95: should go to 5, hit 0 once
  
  ;; Test with sample instructions
  (count-dial-zero-is-hit test-instructions)
  
  ;; Test with file input (once we get it working)
  (def input-data (parse-instructions-from-file "./input.txt"))
  input-data
  (count-dial-zero-is-hit input-data)
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
  (let [step-fn (if (= direction \R) inc dec)
        positions (take (inc steps) 
                       (iterate #(mod (step-fn %) dial-size) current-pos))
        final-pos (last positions)
        zero-hits (count (filter zero? (rest positions)))]
    [final-pos zero-hits]))

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