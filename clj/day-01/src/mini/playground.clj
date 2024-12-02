(ns mini.playground)

; This project has custom configuration.
; See .vscode/settings.json

; If you are new to Calva, you may want to use the command:
; Calva: Create a “Getting Started” REPL project
; which creates a project with a an interactive Calva (and Clojure) guide.
(+ 1 1)

(defn fib [n]
  (if (<= n 1)
    n
    (+ (fib (- n 1)) (fib (- n 2)))))

(fib 20)

; Another version of it with threads
(defn fib2 [n]
  (if (<= n 1)
    n
    (let [f1 (future (fib2 (- n 1)))
          f2 (future (fib2 (- n 2)))]
      (+ @f1 @f2))))

(fib2 20)