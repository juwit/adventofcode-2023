package com.github.juwit.adventofcode2023

import java.util.List.copyOf
import kotlin.math.abs

class Day11: Day(11, "Cosmic Expansion") {

    class Universe(val input: List<String>) {
        val galaxies : List<Galaxy> = mutableListOf()

        init {
            for(y in input.indices){
                for(x in 0..<input[y].length){
                    if(input[y][x] == '#'){
                        galaxies.addLast(Galaxy(y,x))
                    }
                }
            }
        }

        fun expand(expansion: Int = 2): Universe {
            // find lines without galaxies
            input.indices
                .filter { galaxies.none { gal -> gal.y == it } }
                // expanding in reverse order to prevent shifting
                .reversed()
                .forEach { galaxies.filter { gal -> gal.y > it }.forEach { gal -> gal.y+=expansion-1 } }
            // find columns without galaxies
            input[0].indices
                .filter { galaxies.none { gal -> gal.x == it } }
                // expanding in reverse order to prevent shifting
                .reversed()
                .forEach { galaxies.filter { gal -> gal.x > it }.forEach { gal -> gal.x+=expansion-1 } }
            return this
        }

        fun allShortestDistances(): Long {
            var distance = 0L
            while(galaxies.isNotEmpty()){
                val galaxy = galaxies.removeFirst()
                // compute distance to others
                distance += galaxies.sumOf { it.distanceTo(galaxy) }
            }
            return distance
        }
    }

    data class Galaxy(var y: Int, var x: Int) {
        fun distanceTo(other: Day11.Galaxy): Long {
            return (abs(this.x - other.x) + abs(this.y - other.y)).toLong()
        }
    }

    override fun solvePart1(input: List<String>): Number {
        return Universe(input)
            .expand()
            .allShortestDistances()
    }

    override fun solvePart2(input: List<String>): Number {
        return Universe(input)
            .expand(1_000_000)
            .allShortestDistances()
    }
}