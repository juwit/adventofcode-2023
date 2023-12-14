package com.github.juwit.adventofcode2023

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day8Test {

    val exampleInput = """
        RL

        AAA = (BBB, CCC)
        BBB = (DDD, EEE)
        CCC = (ZZZ, GGG)
        DDD = (DDD, DDD)
        EEE = (EEE, EEE)
        GGG = (GGG, GGG)
        ZZZ = (ZZZ, ZZZ)
    """.trimIndent().asStringList()

    val secondExampleInput = """
        LLR

        AAA = (BBB, BBB)
        BBB = (AAA, ZZZ)
        ZZZ = (ZZZ, ZZZ)
    """.trimIndent().asStringList()

    @Test
    fun shouldParseNodesGraph(){
        val network = Day8.Network(exampleInput.subList(2, exampleInput.size))

        assertThat(network.nodesMap).hasSize(7)
        assertThat(network.nodesMap.get("AAA")).isEqualTo(Pair("BBB", "CCC"))
    }

    @Test
    fun shouldWalkTheGraphToZZZ(){
        val network = Day8.Network(exampleInput.subList(2, exampleInput.size))
        assertThat(network.navigate("RL")).isEqualTo(2)
    }

    @Test
    fun shouldWalkTheSecondGraphToZZZ(){
        val network = Day8.Network(secondExampleInput.subList(2, secondExampleInput.size))
        assertThat(network.navigate("LLR")).isEqualTo(6)
    }

    @Test
    fun shouldSolvePart1(){
        assertThat(Day8().solvePart1(exampleInput)).isEqualTo(2L)
        assertThat(Day8().solvePart1(secondExampleInput)).isEqualTo(6L)
    }

}
