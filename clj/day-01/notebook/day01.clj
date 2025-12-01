(ns day01
  (:require [nextjournal.clerk :as clerk]
            [solution :as sol]
            [clojure.string :as str]
            [clojure.repl]))

;; # 🎄 Advent of Code 2024 - Day 01: The Safe

;; ## Problem Description
;; We need to crack a safe with a dial. The dial has numbers from 0 to 99.
;; We start at position **50**.
;; We receive a list of instructions like `L68`, `R48`.
;; * `L` means turn Left (counter-clockwise, decreasing numbers).
;; * `R` means turn Right (clockwise, increasing numbers).
;;
;; The password is the number of times the dial lands on **0** after a rotation.

;; ## 1. Parsing Instructions
;; First, we need to parse the input strings into something usable.
;; An instruction like "L68" has a direction and a number of steps.

(clerk/code (with-out-str (clojure.repl/source sol/parse-instruction)))

;; Let's test it:
(sol/parse-instruction "L68")
(sol/parse-instruction "R48")

;; ## 2. Moving the Dial
;; The dial is circular (0-99).
;; * Moving Right (`R`) adds to the position.
;; * Moving Left (`L`) subtracts from the position.
;; We use modulo arithmetic to wrap around.

(clerk/code (with-out-str (clojure.repl/source sol/calculate-next-pos)))

;; Examples:
(sol/calculate-next-pos 50 \L 68 100) ;; Should be 82
(sol/calculate-next-pos 52 \R 48 100) ;; Should be 0

;; ## 3. The Solution
;; We process all instructions, keeping track of the current position.
;; We count how many times the *final* position of a rotation is 0.

(clerk/code (with-out-str (clojure.repl/source sol/count-dial-zero-is-hit)))

;; ## 4. Visualization
;; Let's visualize the dial movement for the example input.

(def example-instructions ["L68" "L30" "R48" "L5" "R60" "L55" "L1" "L99" "R14" "L82"])

;; We can generate the sequence of states (positions) to animate them.

(defn get-states [instructions]
  (reductions
    (fn [current-pos instruction]
      (let [{:keys [direction steps]} (sol/parse-instruction instruction)]
        (sol/calculate-next-pos current-pos direction steps 100)))
    50
    instructions))

(def states (get-states example-instructions))

;; ### Interactive Dial
;; Use the slider to move through the steps.

(clerk/with-viewer
  {:transform-fn clerk/mark-presented
   :render-fn '(fn [states]
                 (let [idx (nextjournal.clerk.render.hooks/use-state 0)
                       current-pos (nth states @idx)
                       dial-size 100
                       angle (* (/ current-pos dial-size) 360)]
                   [:div.flex.flex-col.items-center.gap-4.p-4.border.rounded-lg.bg-slate-50
                    [:h3.text-lg.font-bold "Safe Dial State Machine"]
                    [:div.relative.w-64.h-64.rounded-full.border-8.border-slate-300.flex.items-center.justify-center.bg-white.shadow-xl
                     ;; Dial numbers (simplified)
                     [:div.absolute.top-2.text-xs.text-slate-400.font-mono "0"]
                     [:div.absolute.bottom-2.text-xs.text-slate-400.font-mono "50"]
                     [:div.absolute.right-2.text-xs.text-slate-400.font-mono "25"]
                     [:div.absolute.left-2.text-xs.text-slate-400.font-mono "75"]
                     
                     ;; The Pointer
                     [:div.absolute.w-1.h-28.bg-red-500.origin-bottom.rounded
                      {:style {:transform (str "rotate(" angle "deg)")
                               :bottom "50%"
                               :transition "transform 0.5s cubic-bezier(0.4, 0.0, 0.2, 1)"}}]
                     
                     ;; Center cap
                     [:div.absolute.w-6.h-6.bg-slate-700.rounded-full.z-10]
                     
                     ;; Value display
                     [:div.absolute.text-3xl.font-bold.text-slate-700.mt-32.font-mono
                      (str current-pos)]]
                    
                    [:div.flex.gap-2.items-center
                     [:button.px-4.py-2.bg-blue-600.text-white.rounded.hover:bg-blue-700.disabled:opacity-50
                      {:on-click #(reset! idx (max 0 (dec @idx)))
                       :disabled (= @idx 0)} "Prev"]
                     [:span.font-mono.text-lg.w-24.text-center (str "Step " @idx)]
                     [:button.px-4.py-2.bg-blue-600.text-white.rounded.hover:bg-blue-700.disabled:opacity-50
                      {:on-click #(reset! idx (min (dec (count states)) (inc @idx)))
                       :disabled (= @idx (dec (count states)))} "Next"]]
                    
                    [:div.h-8.flex.items-center
                     (if (= current-pos 0) 
                       [:span.text-green-600.font-bold.text-xl "🎉 HIT ZERO! 🎉"] 
                       [:span.text-slate-400 "Moving..."])]
                    ]))}
  states)
