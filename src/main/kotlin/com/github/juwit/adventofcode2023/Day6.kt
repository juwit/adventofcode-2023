package com.github.juwit.adventofcode2023

class Day6 : Day(6, "Wait For It") {

    data class Race(val time: Long, val distance: Long) {
        fun validWaysToBeatTheRecord(): Int {
            var validWays = 0;
            for (timeButtonPressed in 1..<time) {
                // find if the record is beaten
                if( (time - timeButtonPressed) * timeButtonPressed > distance){
                    validWays++
                }
            }
            return validWays
        }
    }

    fun parseRaces(input: List<String>): List<Race> {
        val times = input[0]
            .substringAfter(":")
            .split(" ")
            .filterNot { it.isBlank() }
            .map { it.toLong() }

        val distances = input[1]
            .substringAfter(":")
            .split(" ")
            .filterNot { it.isBlank() }
            .map { it.toLong() }

        return times.zip(distances)
            .map { Race(it.first, it.second) }
    }

    fun parseSingleRace(input: List<String>): Race {
        val time = input[0]
            .substringAfter(":")
            .split(" ")
            .filterNot { it.isBlank() }
            .joinToString(separator = "")
            .toLong()

        val distance = input[1]
            .substringAfter(":")
            .split(" ")
            .filterNot { it.isBlank() }
            .joinToString(separator = "")
            .toLong()

        return Race(time, distance)
    }

    override fun solvePart1(input: List<String>): Number {
        val races = parseRaces(input)
        return races
            .map { it.validWaysToBeatTheRecord() } // find ways
            .reduce { a, b -> a * b } // multiply all values
    }

    override fun solvePart2(input: List<String>): Number {
        return parseSingleRace(input).validWaysToBeatTheRecord()
    }
}
