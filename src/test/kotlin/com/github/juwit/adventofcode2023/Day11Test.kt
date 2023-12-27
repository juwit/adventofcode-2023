package com.github.juwit.adventofcode2023

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day11Test {

    private val input = """
        ...#......
        .......#..
        #.........
        ..........
        ......#...
        .#........
        .........#
        ..........
        .......#..
        #...#.....
    """.trimIndent().asStringList().filterNot { it.isBlank() }

    @Test
    fun itShouldParseTheUniverse(){
        val universe = Day11.Universe(input)
        assertThat(universe.galaxies[0]).isEqualTo(Day11.Galaxy(0, 3))
    }

    @Test
    fun itShouldExpandTheUniverse(){
        val universe = Day11.Universe(input)
        universe.expand()
        assertThat(universe.galaxies[0]).isEqualTo(Day11.Galaxy(0, 4))
        assertThat(universe.galaxies[1]).isEqualTo(Day11.Galaxy(1, 9))
        assertThat(universe.galaxies[2]).isEqualTo(Day11.Galaxy(2, 0))
        assertThat(universe.galaxies[3]).isEqualTo(Day11.Galaxy(5, 8))
        assertThat(universe.galaxies[4]).isEqualTo(Day11.Galaxy(6, 1))
        assertThat(universe.galaxies[5]).isEqualTo(Day11.Galaxy(7, 12))
        assertThat(universe.galaxies[6]).isEqualTo(Day11.Galaxy(10, 9))
        assertThat(universe.galaxies[7]).isEqualTo(Day11.Galaxy(11, 0))
        assertThat(universe.galaxies[8]).isEqualTo(Day11.Galaxy(11, 5))
    }

    @Test
    fun itFindShortestDistanceBetweenTwoGalaxies(){
        val galaxy1 = Day11.Galaxy(0, 4)
        val galaxy3 = Day11.Galaxy(2, 0)
        val galaxy6 = Day11.Galaxy(7, 12)
        val galaxy7 = Day11.Galaxy(10, 9)
        val galaxy8 = Day11.Galaxy(11, 0)
        val galaxy9 = Day11.Galaxy(11, 5)
        assertThat(galaxy1.distanceTo(galaxy7)).isEqualTo(15)
        assertThat(galaxy3.distanceTo(galaxy6)).isEqualTo(17)
        assertThat(galaxy8.distanceTo(galaxy9)).isEqualTo(5)
    }

    @Test
    fun itShouldFindShortestDistanceBetweenAllGalaxies(){
        val universe = Day11.Universe(input)
        universe.expand()
        assertThat(universe.allShortestDistances()).isEqualTo(374)
    }

    @Test
    fun itShouldSolvePart1(){
        assertThat(Day11().solvePart1(input)).isEqualTo(374L)
    }

}