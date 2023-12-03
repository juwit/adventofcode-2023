package com.github.juwit.adventofcode2023

import java.util.function.BiFunction
import kotlin.math.max
import kotlin.math.min

class Day2 : Day(2, "Cube Conundrum") {

    class Game(spec: String) {
        private val gameRegex = Regex("Game (\\d+):.*")

        val roundRegex = Regex("(.*)")

        val id: Int

        private val observedCubes = HashMap<String, Int>()

        fun redCubes() = observedCubes.getOrDefault("red", 0)
        fun greenCubes() = observedCubes.getOrDefault("green", 0)
        fun blueCubes() = observedCubes.getOrDefault("blue", 0)

        fun isPossible(redCubes: Int, greenCubes: Int, blueCubes: Int): Boolean{
            return redCubes >= redCubes() && greenCubes >= greenCubes() && blueCubes >= blueCubes()
        }

        fun cubePower(): Int {
            var power = 1
            observedCubes.values.forEach { power *= it }
            return power
        }

        init {
            // get game id from first regex
            val regexMatchGroups = gameRegex.matchEntire(spec)
            id = regexMatchGroups!!.groupValues[1].toInt()

            // parseRounds
            // add a trailing ';' to help parsing
            val rounds = spec.substringAfter(":")
                    .split(";")
                    .map { it.trim() }

            rounds
                    .forEach {
                        it.split(",")
                                .map { it.trim() }
                                .forEach {
                                    val (countStr, color) = it.split(" ");
                                    val count = countStr.toInt()

                                    observedCubes.computeIfPresent(color) { _, u -> max(u, count) }
                                    observedCubes.putIfAbsent(color, count)
                                }
                    }
        }
    }

    override fun solvePart1(input: List<String>): Number {
        return input.map { Game(it) }
                .filter { it.isPossible(12,13,14) }
                .sumOf { it.id }
    }

    override fun solvePart2(input: List<String>): Number {
        return input.map { Game(it) }.sumOf { it.cubePower() }
    }
}