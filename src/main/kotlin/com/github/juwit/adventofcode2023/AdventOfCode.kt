package com.github.juwit.adventofcode2023

import picocli.CommandLine
import java.lang.IllegalArgumentException
import java.util.concurrent.Callable
import kotlin.system.exitProcess

@CommandLine.Command(name = "adventofcode-2023")
class AdventOfCode : Callable<Int> {

    @CommandLine.Option(names = ["-d", "--day"], description = ["the day to run"])
    private var dayNumber = 0

    override fun call(): Int {
        println("[AdventOfCode - 2023]")
        println()


        val days: List<Day> = listOf(
            Day1(),
            Day2(),
            Day3(),
            Day4(),
            Day5(),
            Day6(),
            Day7(),
            Day8(),
            Day9(),
            Day11()
        )

        if (dayNumber == 0) {
            dayNumber = days.last().id
        }

        val day = days.find { it.id == dayNumber } ?: throw IllegalArgumentException("could not find day #$dayNumber")
        day.solveDay()
        return 0
    }

}

fun main(args: Array<String>) {
    exitProcess(CommandLine(AdventOfCode()).execute(*args))
}
