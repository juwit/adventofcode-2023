package com.github.juwit.adventofcode2023

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day2Test {

    val input = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """.trimIndent().asStringList()

    @Test
    fun part1_should_parse_a_game() {
        val game1 = Day2.Game("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");
        assertThat(game1.id).isEqualTo(1)
        assertThat(game1.blueCubes()).isEqualTo(6)
        assertThat(game1.redCubes()).isEqualTo(4)
        assertThat(game1.greenCubes()).isEqualTo(2)
    }

    @Test
    fun part1_should_compute_possible_games(){

    }

    @Test
    fun part1_should_compute_sum_of_possible_games(){
        val day2 = Day2()
        assertThat(day2.solvePart1(input)).isEqualTo(8)
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