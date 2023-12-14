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

        fun ghostNavigate(fromNode: String, instructions: String) : Long{
            var instructionIndex = 0L
            var currentNode = fromNode

            while (!currentNode.endsWith("Z")){

                val direction = instructions[(instructionIndex % instructions.length).toInt()]
                instructionIndex++

                currentNode = when(direction){
                    'L' -> nodesMap[currentNode]!!.first
                    'R' -> nodesMap[currentNode]!!.second
                    else -> throw IllegalArgumentException("Not a valid direction")
                }


            }

            return instructionIndex
        }

        fun navigate(instructions: String): Long{
            var currentNode = "AAA"

            var instructionIndex = 0L

            while (currentNode != "ZZZ"){

                val direction = instructions[(instructionIndex % instructions.length).toInt()]
                instructionIndex++

                currentNode = when(direction){
                    'L' -> nodesMap[currentNode]!!.first
                    'R' -> nodesMap[currentNode]!!.second
                    else -> throw IllegalArgumentException("Not a valid direction")
                }


            }

            return instructionIndex
        }
    }

    override fun solvePart1(input: List<String>): Number {
        val network = Network(input.subList(1, input.size))
        return network.navigate(input[0])
    }

    fun gcd(a: Long, b: Long): Long {
        return if (b==0L) a else gcd(b, a % b)
    }

    fun lcm(a: Long, b: Long): Long {
        return a * (b / gcd(a, b))
    }

    fun lcmOfList(numbers: List<Long>): Long {
        var result = numbers[0]
        for (i in 1 until numbers.size) {
            result = lcm(result, numbers[i])
        }
        return result
    }

    override fun solvePart2(input: List<String>): Number {
        val network = Network(input.subList(1, input.size))
        val instructions = input[0]

        val steps = network.nodesMap.keys.filter { it.endsWith("A") }
            .map { network.ghostNavigate(it, instructions) }

        return lcmOfList(steps)
    }
}
