(ns test
  (:require [clojure.test :refer [deftest is]]
            [solution :refer [count-dial-zero-is-hit]]))

(def input-data
  ["L68"
   "L30"
   "R48"
   "L5"
   "R60"
   "L55"
   "L1"
   "L99"
   "R14"
   "L82"])

(deftest test-count-dial-zero-is-hit
  (is (= 3 (count-dial-zero-is-hit input-data))))

(comment 
  (count-dial-zero-is-hit input-data)
  
  )
