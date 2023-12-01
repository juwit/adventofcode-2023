package com.github.juwit.adventofcode2023

class Day1 : Day(1, "Trebuchet?!") {

    private fun part1LineValue(line: String): Int {
        val digits = line.toCharArray()
                .filter { it.isDigit() }
                .map { it.digitToInt() }
        return digits.first()*10 + digits.last();
    }

    private fun part2LineValue(line: String): Int {
        return part1LineValue(line
                // shitting ugly replace, to keep letters that could be in common
                .replace("one", "o1e")
                .replace("two", "t2o")
                .replace("three", "t3e")
                .replace("four", "f4r")
                .replace("five", "f5e")
                .replace("six", "s6x")
                .replace("seven", "s7n")
                .replace("eight", "e8t")
                .replace("nine", "n9e")
        )
    }

    override fun solvePart1(input: List<String>): Int {
        return input.sumOf { part1LineValue(it) }
    }

    override fun solvePart2(input: List<String>): Int {
        return input.sumOf { part2LineValue(it) }
    }

}
