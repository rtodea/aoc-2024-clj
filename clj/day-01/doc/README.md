# Day 01 --- Development Notes

As requested by the authors of the Advent of Code puzzles, I am not including the actual text of the puzzles in this repository. Please visit https://adventofcode.com to view the puzzles.

> https://adventofcode.com/2025/day/1

## Notes

There is a password that I need to figure out.

The password is in a safe with a dial.

The dial looks like this (ASCII art of a dial, that has numbers from 0 to 22).

The dial starts at `0` to `99`.

For example, the following one starts from `0` to `22`:

```ascii
              21  22  0   1   2   3
           20                          4
         19                              5
        18                                6
       17                                  7
       16                                  8
        15                                9
         14                            10
           13                        11
              12  11  10  9   8   7
```

The dial is turned according to a list of instructions.

Each instruction is a number that indicates how many steps to turn the dial.

For example, if the instruction is `R5`, the dial is turned `5` steps clockwise.

If the instruction is `L3`, the dial is turned `3` steps counter-clockwise.

The password is determined by how many times the dial hits `0` during the entire sequence of instructions.
