package com.github.juwit.adventofcode2023

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day6Test {

    val exampleInput = """
        Time:      7  15   30
        Distance:  9  40  200
    """.trimIndent().asStringList()

    @Test
    fun shouldParseRaceInfo(){
        val races = Day6().parseRaces(exampleInput)

        assertThat(races).hasSize(3)

        assertThat(races[0].time).isEqualTo(7)
        assertThat(races[0].distance).isEqualTo(9)

        assertThat(races[1].time).isEqualTo(15)
        assertThat(races[1].distance).isEqualTo(40)

        assertThat(races[2].time).isEqualTo(30)
        assertThat(races[2].distance).isEqualTo(200)
    }

    @Test
    fun shouldFindNumberOfValidWay(){
        assertThat(Day6.Race(7, 9).validWaysToBeatTheRecord()).isEqualTo(4)
        assertThat(Day6.Race(15, 40).validWaysToBeatTheRecord()).isEqualTo(8)
        assertThat(Day6.Race(30, 200).validWaysToBeatTheRecord()).isEqualTo(9)
    }

    @Test
    fun shouldSolvePart1(){
        assertThat(Day6().solvePart1(exampleInput)).isEqualTo(288)
    }

    @Test
    fun shouldParseSingleRace(){
        val race = Day6().parseSingleRace(exampleInput)
        assertThat(race.time).isEqualTo(71530)
        assertThat(race.distance).isEqualTo(940200)
    }

    @Test
    fun shouldSolvePart2(){
        assertThat(Day6().solvePart2(exampleInput)).isEqualTo(71503)
    }

}
