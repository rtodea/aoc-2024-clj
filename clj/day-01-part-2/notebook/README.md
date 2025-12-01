# 📓 Advent of Code Notebooks - Day 01 Part 2

This folder contains Literate Programming notebooks using [Clerk](https://github.com/nextjournal/clerk).

## 🚀 Running the Notebooks

You can run the notebooks using Docker to explore the solutions and visualizations interactively.

### 1. Build the Docker Image

From the `clj/day-01-part-2` directory:

```bash
docker build -f Dockerfile.clerk -t aoc-2024-clj-day01-part2-clerk .
```

### 2. Run the Container

```bash
docker run -p 7777:7777 aoc-2024-clj-day01-part2-clerk
```

### 3. Access the Notebook

Open your browser at **http://localhost:7777** and select `day01-part2.clj`.

## 📝 Notebook Contents

*   **`day01-part2.clj`**: Solution for Day 01 Part 2, including:
    *   Problem breakdown
    *   Code explanation
    *   Interactive dial visualization with hit counter

## 🛠️ Development

If you want to modify the notebook and see changes live, you can mount the notebook directory:

```bash
docker run -p 7777:7777 -v $(pwd)/notebook:/app/notebook aoc-2024-clj-day01-part2-clerk
```

Clerk is configured to watch for file changes, so the browser should update automatically when you save `day01-part2.clj`.