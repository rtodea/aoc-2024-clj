(ns test
  (:require [clojure.test :refer [deftest is testing]]
            [solution :refer [count-dial-zero-is-hit parse-instruction calculate-next-pos]]))

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

(deftest test-parse-instruction
  (testing "Parsing instructions"
    (is (= {:direction \L :steps 68} (parse-instruction "L68")))
    (is (= {:direction \R :steps 48} (parse-instruction "R48")))))

(deftest test-calculate-next-pos
  (testing "Calculating next position"
    (is (= 82 (calculate-next-pos 50 \L 68 100)))
    (is (= 52 (calculate-next-pos 82 \L 30 100)))
    (is (= 0 (calculate-next-pos 52 \R 48 100)))
    ;; Edge cases
    (is (= 0 (calculate-next-pos 0 \L 0 100)))
    (is (= 1 (calculate-next-pos 0 \R 1 100)))
    (is (= 99 (calculate-next-pos 0 \L 1 100)))))

(deftest test-count-dial-zero-is-hit
  (testing "Counting zero hits with example data"
    (is (= 3 (count-dial-zero-is-hit input-data)))))

(comment 
  (clojure.test/run-tests)
  )
