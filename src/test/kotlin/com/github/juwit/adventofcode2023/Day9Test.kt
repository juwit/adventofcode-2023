package com.github.juwit.adventofcode2023

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day9Test {

    val exampleInput = """
        0 3 6 9 12 15
        1 3 6 10 15 21
        10 13 16 21 30 45
    """.trimIndent().asStringList().filterNot { it.isBlank() }

    @Test
    fun shouldParseAHistory() {
        assertThat(Day9.History(exampleInput[0]).values).containsExactly(0L, 3L, 6L, 9L, 12L, 15L)
    }

    @Test
    fun shouldFinNextValue() {
        assertThat(Day9.History(exampleInput[0]).nextValue()).isEqualTo(18L)
        assertThat(Day9.History(exampleInput[1]).nextValue()).isEqualTo(28L)
        assertThat(Day9.History(exampleInput[2]).nextValue()).isEqualTo(68L)
    }

    @Test
    fun shouldSolvePart1() {
        assertThat(Day9().solvePart1(exampleInput)).isEqualTo(114L)
    }

    @Test
    fun shouldFinPreviousValue() {
        assertThat(Day9.History(exampleInput[0]).previousValue()).isEqualTo(-3L)
        assertThat(Day9.History(exampleInput[1]).previousValue()).isEqualTo(0L)
        assertThat(Day9.History(exampleInput[2]).previousValue()).isEqualTo(5L)
    }

    @Test
    fun shouldSolvePart2() {
        assertThat(Day9().solvePart2(exampleInput)).isEqualTo(2L)
    }

}
