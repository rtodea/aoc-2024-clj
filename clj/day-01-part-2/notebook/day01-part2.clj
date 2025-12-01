(ns day01-part2
  (:require [nextjournal.clerk :as clerk]
            [solution :as sol]
            [clojure.string :as str]
            [clojure.repl]))

;; # 🎄 Advent of Code 2024 - Day 01 Part 2: The Click Method

;; ## Problem Description
;; We found a new security protocol: **Method 0x434C49434B**.
;; Instead of checking the final position, we must count **every time** the dial hits **0** during a rotation.
;;
;; * Start at position **50**.
;; * Count hits to 0 during the entire rotation path.
;; * Example: `R1000` from 50 hits 0 ten times!

;; ## 1. Counting Zeros in Rotation
;; We need a function that simulates the full path of a rotation and counts zeros.

(clerk/code (with-out-str (clojure.repl/source sol/count-zeros-in-rotation)))

;; Examples:
(sol/count-zeros-in-rotation 50 \L 68 100)   ;; Hits 0 once, ends at 82
(sol/count-zeros-in-rotation 95 \R 60 100)   ;; Hits 0 once, ends at 55
(sol/count-zeros-in-rotation 50 \R 1000 100) ;; Hits 0 ten times!

;; ## 2. The Solution
;; We iterate through all instructions, accumulating the total hits.

(clerk/code (with-out-str (clojure.repl/source sol/count-dial-zero-is-hit)))

;; ## 3. Visualization
;; Let's visualize the path and the hits.

(def example-instructions ["L68" "L30" "R48" "L5" "R60" "L55" "L1" "L99" "R14" "L82"])

(defn get-detailed-states [instructions]
  (reductions
    (fn [{:keys [pos total-hits]} instruction]
      (let [{:keys [direction steps]} (sol/parse-instruction instruction)
            [new-pos hits] (sol/count-zeros-in-rotation pos direction steps 100)]
        {:pos new-pos 
         :total-hits (+ total-hits hits)
         :last-hits hits
         :instruction instruction}))
    {:pos 50 :total-hits 0 :last-hits 0 :instruction "START"}
    instructions))

(def states (get-detailed-states example-instructions))

;; ### Interactive Dial with Hit Counter
(clerk/with-viewer
  {:transform-fn clerk/mark-presented
   :render-fn '(fn [states]
                 (let [idx (nextjournal.clerk.render.hooks/use-state 0)
                       state (nth states @idx)
                       current-pos (:pos state)
                       dial-size 100
                       angle (* (/ current-pos dial-size) 360)]
                   [:div.flex.flex-col.items-center.gap-4.p-4.border.rounded-lg.bg-slate-50
                    [:h3.text-lg.font-bold "Method 0x434C49434B"]
                    
                    [:div.grid.grid-cols-2.gap-4.w-full.max-w-md
                     [:div.bg-white.p-2.rounded.shadow.text-center
                      [:div.text-xs.text-slate-500 "Instruction"]
                      [:div.font-mono.font-bold (:instruction state)]]
                     [:div.bg-white.p-2.rounded.shadow.text-center
                      [:div.text-xs.text-slate-500 "Hits this turn"]
                      [:div.font-mono.font-bold.text-blue-600 (:last-hits state)]]]

                    [:div.relative.w-64.h-64.rounded-full.border-8.border-slate-300.flex.items-center.justify-center.bg-white.shadow-xl
                     ;; Dial numbers
                     [:div.absolute.top-2.text-xs.text-slate-400.font-mono "0"]
                     [:div.absolute.bottom-2.text-xs.text-slate-400.font-mono "50"]
                     
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
                    
                    [:div.bg-yellow-100.p-4.rounded-lg.border.border-yellow-300.w-full.text-center
                     [:div.text-sm.text-yellow-800.uppercase.tracking-wider "Total Password Value"]
                     [:div.text-4xl.font-bold.text-yellow-900 (:total-hits state)]]
                    
                    [:div.flex.gap-2.items-center
                     [:button.px-4.py-2.bg-blue-600.text-white.rounded.hover:bg-blue-700.disabled:opacity-50
                      {:on-click #(reset! idx (max 0 (dec @idx)))
                       :disabled (= @idx 0)} "Prev"]
                     [:span.font-mono.text-lg.w-24.text-center (str "Step " @idx)]
                     [:button.px-4.py-2.bg-blue-600.text-white.rounded.hover:bg-blue-700.disabled:opacity-50
                      {:on-click #(reset! idx (min (dec (count states)) (inc @idx)))
                       :disabled (= @idx (dec (count states)))} "Next"]]
                    ]))}
  states)
