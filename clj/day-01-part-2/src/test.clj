(ns test
  (:require [clojure.test :refer [deftest is testing]]
            [solution :refer [count-dial-zero-is-hit parse-instruction count-zeros-in-rotation]]))

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

(deftest test-count-zeros-in-rotation
  (testing "Counting zeros during rotation"
    ;; L68 from 50 -> 82 (hits 0 once)
    (is (= [82 1] (count-zeros-in-rotation 50 \L 68 100)))
    ;; L30 from 82 -> 52 (no hits)
    (is (= [52 0] (count-zeros-in-rotation 82 \L 30 100)))
    ;; R48 from 52 -> 0 (hits 0 once at end)
    (is (= [0 1] (count-zeros-in-rotation 52 \R 48 100)))
    ;; R60 from 95 -> 55 (hits 0 once during)
    (is (= [55 1] (count-zeros-in-rotation 95 \R 60 100)))
    ;; R1000 from 50 -> 50 (hits 0 ten times)
    (is (= [50 10] (count-zeros-in-rotation 50 \R 1000 100)))))

(deftest test-count-dial-zero-is-hit
  (testing "Counting total zero hits with example data"
    (is (= 6 (count-dial-zero-is-hit input-data)))))

(comment 
  (clojure.test/run-tests)
  )
