package com.github.juwit.adventofcode2023

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day1Test {

    val testInput = """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
    """.trimIndent().asStringList()

    val testInputPart2 = """
        two1nine
        eightwothree
        abcone2threexyz
        xtwone3four
        4nineeightseven2
        zoneight234
        7pqrstsixteen
    """.trimIndent().asStringList()

    @Test
    fun part1_should_find_matching_entries() {
        val day1 = Day1()

        assertEquals(142, day1.solvePart1(testInput))
    }

    @Test
    fun part2_should_find_matching_entries() {
        val day1 = Day1()

        assertEquals(281, day1.solvePart2(testInputPart2))
    }

}
