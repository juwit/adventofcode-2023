package com.github.juwit.adventofcode2023

class Day3: Day(3, "Gear Ratios") {

    class Grid(val numbers: List<NumberCell> = mutableListOf(), val symbols: List<SymbolCell> = mutableListOf())

    companion object {
        val numberRegex = Regex("(\\d+)") // find numbers
        val symbolRegex = Regex("([^\\d\\.])") // find symbols
    }

    data class NumberCell(val value: Int, val x: Int, val y: Int, val length: Int) {
        fun hasAdjacentSymbol(input: List<String>): Boolean{
            // build a rectangle
            val minX = maxOf(0, x-1)
            val minY = maxOf(0, y-1)
            val maxX = minOf(input[0].length-1, x+length)
            val maxY = minOf(input.size-1, y+1)
            for (i in minX..maxX){
                for(j in minY..maxY){
                    if( symbolRegex.matches(input[j][i].toString()) ) {
                        return true
                    }
                }
            }
            return false
        }
    }

    data class SymbolCell(val char: String, val x: Int, val y: Int)

    fun parseGrid(input: List<String>): Grid{
        val grid = Grid()
        var y = 0

        input.forEach {
            numberRegex.findAll(it).forEach {
                grid.numbers.addLast(NumberCell(it.value.toInt(), it.range.first, y, it.value.length))
            }
            symbolRegex.findAll(it).forEach {
                grid.symbols.addLast(SymbolCell(it.value, it.range.first, y))
            }
            y++
        }
        return grid
    }

    override fun solvePart1(input: List<String>): Number {
        val grid = parseGrid(input)
        return grid.numbers.filter { it.hasAdjacentSymbol(input) }.sumOf { it.value }
    }

    override fun solvePart2(input: List<String>): Number {
        return 0
    }

}
