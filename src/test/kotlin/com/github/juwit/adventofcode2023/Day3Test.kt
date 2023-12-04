package com.github.juwit.adventofcode2023

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.`in`
import org.junit.jupiter.api.Test

class Day3Test {

    val input = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...*.*....
            .664.598..
        """.trimIndent().asStringList()

    @Test
    fun part1_should_parse_a_grid() {
        val grid = Day3().parseGrid(input)
        assertThat(grid.numbers.map { it.value }).containsExactly(467, 114, 35, 633, 617, 58, 592, 755, 664, 598)
        assertThat(grid.symbols.map { it.char }).containsExactly("*", "#", "*", "+", "*", "*")
    }

    @Test
    fun part1_should_compute_sum_of_adjacent_numbers(){
        val day3 = Day3()
        assertThat(day3.solvePart1(input)).isEqualTo(4361)
    }

    @Test
    fun part2_should_compute_cube_powers(){
        assertThat(Day2.Game(input[0]).cubePower()).isEqualTo(48)
        assertThat(Day2.Game(input[1]).cubePower()).isEqualTo(12)
        assertThat(Day2.Game(input[2]).cubePower()).isEqualTo(1560)
        assertThat(Day2.Game(input[3]).cubePower()).isEqualTo(630)
        assertThat(Day2.Game(input[4]).cubePower()).isEqualTo(36)
    }

    @Test
    fun part2_should_compute_sum_of_possible_cube_powers(){
        val day2 = Day2()
        assertThat(day2.solvePart2(input)).isEqualTo(2286)
    }

}
