FROM clojure:temurin-21-tools-deps

WORKDIR /app

# Pre-download dependencies
RUN clojure -P

# We will mount the source code at runtime, so we don't copy specific days here.
# This allows one image to run any day's solution.

CMD ["clj"]
