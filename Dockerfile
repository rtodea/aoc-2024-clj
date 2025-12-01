FROM clojure:temurin-21-tools-deps

WORKDIR /app

# Copy the project configuration for Day 01
COPY clj/day-01/deps.edn .

# Download dependencies
RUN clojure -P

# Copy the source code for Day 01
COPY clj/day-01/src src

# The input file will be mounted at runtime to /app/input.txt
# The command to run is specified in the README.md
CMD ["clj"]
