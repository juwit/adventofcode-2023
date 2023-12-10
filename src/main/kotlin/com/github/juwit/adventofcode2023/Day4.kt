package com.github.juwit.adventofcode2023

import kotlin.math.pow

val cardRegex = Regex("Card +(\\d+)")

class Day4: Day(4, "Scratchcards") {

    class Card(line: String){

        private var winningNumbers: Set<Int>

        private var myNumbers: Set<Int>

        val number: Int

        var copies = 0

        init {
            val match = cardRegex.find(line)
            this.number = match!!.groupValues[1].toInt()

            this.winningNumbers =
                line.split(":")[1] // after the colon
                    .split("|")[0] // before the pipe
                    .split(" ") // space separated
                    .filter { it.isNotBlank() }
                    .map { it.toInt() }
                    .toSet()

            this.myNumbers =
                line.split(":")[1] // after the colon
                    .split("|")[1] // after the pipe
                    .split(" ") // space separated
                    .filter { it.isNotBlank() }
                    .map { it.toInt() }
                    .toSet()
        }

        fun goodNumbers(): Int {
            return myNumbers.intersect(winningNumbers).size
        }

        fun worth(): Int{
            return 2.0.pow(goodNumbers() - 1).toInt()
        }
    }

    override fun solvePart1(input: List<String>): Number {
        return input
            .filter { it.isNotBlank() }
            .map { Card(it) }
            .sumOf { it.worth() }
    }

    override fun solvePart2(input: List<String>): Number {
        val cards = input
            .filter { it.isNotBlank() }
            .map { Card(it) }

        cards.forEach {
            for(j in 0..it.copies) {
                for (i in 1..it.goodNumbers()) {
                    cards[i + it.number - 1].copies++
                }
            }
        }

        return cards.size + cards.sumOf { it.copies }
    }
}