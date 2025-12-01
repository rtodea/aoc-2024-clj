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

## Day 01 Part 2

To run the solution for Day 01 Part 2:

1. Navigate to the directory:
   ```bash
   cd clj/day-01-part-2
   ```

2. Run the tests:
   ```bash
   clj -M -e "(require 'test) (clojure.test/run-tests 'test)"
   ```

3. Run the solution (assuming input.txt is present):
   ```bash
   clj -M -e "(load-file \"src/solution.clj\") (solution/solve \"input.txt\")"
   ```

## Dockerfile approach

Alternatively, you can use the provided Dockerfile to run the solutions in a containerized environment.

1. Build the Docker image with the command:

   ```bash
   docker build -t aoc-2024-clj .
   ```

2. Run the container for **Day 01**:

    ```bash
    docker run -v $(pwd)/clj/day-01/input.txt:/app/input.txt aoc-2024-clj clj -M -e "(load-file \"src/solution.clj\") (solution/solve \"input.txt\")"
    ```

3. Run the container for **Day 01 Part 2**:

    ```bash
    # Note: We mount the Part 2 source code into the container
    docker run -v $(pwd)/clj/day-01-part-2/src:/app/src -v $(pwd)/clj/day-01/input.txt:/app/input.txt aoc-2024-clj clj -M -e "(load-file \"src/solution.clj\") (solution/solve \"input.txt\")"
    ```

## Running Tests

To run the tests locally:

```bash
clj -M -e "(require 'test) (clojure.test/run-tests 'test)"
```

To run the tests using Docker:

```bash
docker run aoc-2024-clj clj -M -e "(load-file \"src/test.clj\") (clojure.test/run-tests 'test)"
```

## Literate Programming with Clerk

You can explore the solution and visualization in a Clerk notebook.

### Day 01

1. Navigate to the day's directory:
   ```bash
   cd clj/day-01
   ```

2. Build the Clerk Docker image:
   ```bash
   docker build -f Dockerfile.clerk -t aoc-2024-clj-clerk .
   ```

3. Run the container:
   ```bash
   docker run -p 7777:7777 aoc-2024-clj-clerk
   ```

4. Open your browser at http://localhost:7777 and select `day01.clj` to see the notebook.

### Day 01 Part 2

1. Navigate to the directory:
   ```bash
   cd clj/day-01-part-2
   ```

2. Build the Clerk Docker image:
   ```bash
   docker build -f Dockerfile.clerk -t aoc-2024-clj-day01-part2-clerk .
   ```

3. Run the container:
   ```bash
   docker run -p 7777:7777 aoc-2024-clj-day01-part2-clerk
   ```

4. Open your browser at http://localhost:7777 and select `day01-part2.clj`.
