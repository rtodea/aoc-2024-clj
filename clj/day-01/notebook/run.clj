(ns run
  (:require [nextjournal.clerk :as clerk]))

(println "Starting Clerk...")
(clerk/serve! {:watch-paths ["."] :port 7777 :address "0.0.0.0" :browse? false})
(println "Clerk started on port 7777")

;; Force show the notebook
(println "Showing day01.clj...")
(clerk/show! "day01.clj")

;; Keep the process alive
@(promise)
