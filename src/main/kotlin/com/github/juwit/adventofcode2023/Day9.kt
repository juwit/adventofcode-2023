package com.github.juwit.adventofcode2023

fun difference(a: Long, b: Long) = b - a

class Day9 : Day(9, "Mirage Maintenance") {

    class History(val values: List<Long>) {

        constructor(input: String) : this(input.split(" ").map { it.toLong() })

        fun nextValue(): Long {
            val last = values.last()
            val zipped = values.zipWithNext(::difference)
            if (zipped.all { it == 0L }) {
                return last
            }
            return last + History(zipped).nextValue()
        }

    }


    override fun solvePart1(input: List<String>): Number {
        return input.map { History(it) }
            .sumOf { it.nextValue() }
    }

    override fun solvePart2(input: List<String>): Number {
        TODO("Not yet implemented")
    }
}
