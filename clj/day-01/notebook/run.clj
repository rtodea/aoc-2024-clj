(ns run
  (:require [nextjournal.clerk :as clerk]))

(println "Starting Clerk...")
;; Try binding to 0.0.0.0 with both :address and :host keys to be safe
(def server (clerk/serve! {:watch-paths ["."] 
                           :port 7777 
                           :address "0.0.0.0" 
                           :host "0.0.0.0" 
                           :browse? false}))

(println "Clerk server started:" server)

;; Force show the notebook
(println "Showing day01.clj...")
(clerk/show! "day01.clj")

;; Keep the process alive
@(promise)
