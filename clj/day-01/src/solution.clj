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
  (def input-data (parse-instructions-from-file "./input.txt"))
  (parse-instructions-from-file "./input.txt")
  )

(defn count-dial-zero-is-hit
    "Counts how many times the dial hits zero."
    [instructions]  
    ;; TODO: implement logic
    3)