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

## Dockerfile approach

Alternatively, you can use the provided Dockerfile to run the solutions in a containerized environment.

1. Build the Docker image with the command:

   ```bash
   docker build -t aoc-2024-clj .
   ```

2. Run the container with the command:

    ```bash
    docker run -v $(pwd)/clj/day-01/input.txt:/app/input.txt aoc-2024-clj clj -M -e "(load-file \"src/solution.clj\") (solve \"input.txt\")"
    ```
