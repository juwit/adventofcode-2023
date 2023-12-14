package com.github.juwit.adventofcode2023

class Day8 : Day(8, "Haunted Wasteland") {

    class Network(input: List<String>) {

        val nodesMap: MutableMap<String, Pair<String, String>> = mutableMapOf()

        fun registerNode(nodeLine: String) {
            val nodeName = nodeLine.split(" = ")[0]
            val nodePairs = nodeLine.split(" = ")[1]
                .split(" ", "(", ")", ",")
                .filterNot { it.isBlank() }
                .chunked(2)
                .map { Pair(it[0], it[1]) }[0]
            nodesMap.put(nodeName, nodePairs)
        }

        init {
            input.forEach { registerNode(it) }
        }

        fun navigate(instructions: String): Long{
            var currentNode = "AAA"

            var instructionIndex = 0L

            while (currentNode != "ZZZ"){

                val direction = instructions[(instructionIndex % instructions.length).toInt()]

                currentNode = when(direction){
                    'L' -> nodesMap[currentNode]!!.first
                    'R' -> nodesMap[currentNode]!!.second
                    else -> "AAA"
                }

                instructionIndex++
            }

            return instructionIndex
        }
    }

    override fun solvePart1(input: List<String>): Number {
        val network = Network(input.subList(1, input.size))
        return network.navigate(input[0])
    }

    override fun solvePart2(input: List<String>): Number {
        TODO("Not yet implemented")
    }
}
