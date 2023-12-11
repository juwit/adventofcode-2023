package com.github.juwit.adventofcode2023

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day5Test {

    val exampleInput = """
        seeds: 79 14 55 13

        seed-to-soil map:
        50 98 2
        52 50 48
        
        soil-to-fertilizer map:
        0 15 37
        37 52 2
        39 0 15
        
        fertilizer-to-water map:
        49 53 8
        0 11 42
        42 0 7
        57 7 4
        
        water-to-light map:
        88 18 7
        18 25 70
        
        light-to-temperature map:
        45 77 23
        81 45 19
        68 64 13
        
        temperature-to-humidity map:
        0 69 1
        1 0 69
        
        humidity-to-location map:
        60 56 37
        56 93 4
    """.trimIndent().asStringList().filter { it.isNotBlank() }

    @Test
    fun shouldParseStartingSeeds(){
        val almanach = Day5.Almanach(exampleInput)

        assertThat(almanach.seeds).containsExactly(79, 14, 55, 13)
    }

    @Test
    fun shouldParseAlmanachEntry(){
        val entry = Day5.AlmanachEntry("50 98 2")

        assertThat(entry.destinationRangeStart).isEqualTo(50)
        assertThat(entry.sourceRangeStart).isEqualTo(98)
        assertThat(entry.rangeLength).isEqualTo(2)
    }

    @Test
    fun shouldReturnNextValues(){
        val firstLine = Day5.AlmanachEntry("50 98 2")
        // seed number 98 corresponds to soil number 50
        assertThat(firstLine.next(98)).isEqualTo(50)
        assertThat(firstLine.next(99)).isEqualTo(51)

        val secondLine = Day5.AlmanachEntry("52 50 48")
        // So, seed number 53 corresponds to soil number 55.
        assertThat(secondLine.next(53)).isEqualTo(55)
    }

    @Test
    fun shouldParseAllEntries(){
        val almanach = Day5.Almanach(exampleInput)

        assertThat(almanach.seeds).hasSize(4)
        assertThat(almanach.seedToSoil).hasSize(2)
        assertThat(almanach.soilToFertilizer).hasSize(3)
        assertThat(almanach.fertilizerToWater).hasSize(4)
        assertThat(almanach.waterToLight).hasSize(2)
        assertThat(almanach.lightToTemperature).hasSize(3)
        assertThat(almanach.temperatureToHumidity).hasSize(2)
        assertThat(almanach.humidityToLocation).hasSize(2)
    }

    @Test
    fun shouldSolvePart1(){
        assertThat(Day5().solvePart1(exampleInput)).isEqualTo(35L)
    }

}
