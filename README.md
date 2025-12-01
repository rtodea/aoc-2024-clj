# aoc-202*-clj

Advent of Code solutions in Clojure and notes about them.

## Instructions to run the solutions

1. Make sure you have Clojure installed on your system. You can follow the instructions at https://clojure.org/guides/getting_started.
2. Clone this repository to your local machine.
3. Navigate to the project directory.
4. Run the Clojure REPL with the command `clj`.
5. Load the solution files for the specific day you want to run.
6. Call the main function for that day's solution, passing in the input file if necessary.
    For example, to run Day 01's solution, you would do something like:

    ```clojure
    (load-file "clj/day-01/src/solution.clj")
    (day-01/solve "clj/day-01/input.txt")
    ```
