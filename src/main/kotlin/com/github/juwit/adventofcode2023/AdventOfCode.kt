package com.github.juwit.adventofcode2023

import picocli.CommandLine
import java.lang.IllegalArgumentException
import java.util.concurrent.Callable
import kotlin.system.exitProcess

@CommandLine.Command(name = "adventofcode-2023")
class AdventOfCode : Callable<Int> {

    @CommandLine.Option(names = ["-d", "--day"], description = ["the day to run"], required = true)
    private var dayNumber = 0

    override fun call(): Int {
        println("[AdventOfCode - 2023]")
        println()

        // autoloading all days
        val days: List<Day> = listOf(
                Day1()
        )

        val day = days.find { it.id == dayNumber } ?: throw IllegalArgumentException("could not find day #$dayNumber")
        day.solveDay()
        return 0
    }

}

fun main(args: Array<String>) {
    exitProcess(CommandLine(AdventOfCode()).execute(*args))
}