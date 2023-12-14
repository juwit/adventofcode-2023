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
    """.trimIndent().asStringList().filterNot { it.isBlank() }

    val secondExampleInput = """
        LLR

        AAA = (BBB, BBB)
        BBB = (AAA, ZZZ)
        ZZZ = (ZZZ, ZZZ)
    """.trimIndent().asStringList().filterNot { it.isBlank() }

    val ghostInput = """
        LR

        11A = (11B, XXX)
        11B = (XXX, 11Z)
        11Z = (11B, XXX)
        22A = (22B, XXX)
        22B = (22C, 22C)
        22C = (22Z, 22Z)
        22Z = (22B, 22B)
        XXX = (XXX, XXX)

    """.trimIndent().trimIndent().asStringList().filterNot { it.isBlank() }

    @Test
    fun shouldParseNodesGraph(){
        val network = Day8.Network(exampleInput.subList(1, exampleInput.size))

        assertThat(network.nodesMap).hasSize(7)
        assertThat(network.nodesMap.get("AAA")).isEqualTo(Pair("BBB", "CCC"))
    }

    @Test
    fun shouldWalkTheGraphToZZZ(){
        val network = Day8.Network(exampleInput.subList(1, exampleInput.size))
        assertThat(network.navigate("RL")).isEqualTo(2)
    }

    @Test
    fun shouldWalkTheSecondGraphToZZZ(){
        val network = Day8.Network(secondExampleInput.subList(1, secondExampleInput.size))
        assertThat(network.navigate("LLR")).isEqualTo(6)
    }

    @Test
    fun shouldSolvePart1(){
        assertThat(Day8().solvePart1(exampleInput)).isEqualTo(2L)
        assertThat(Day8().solvePart1(secondExampleInput)).isEqualTo(6L)
    }

    @Test
    fun shouldGhostWalkTheGraph(){
        val network = Day8.Network(ghostInput.subList(1, ghostInput.size))
        assertThat(network.ghostNavigate("LR")).isEqualTo(6)
    }

    @Test
    fun shouldSolvePart2(){
        assertThat(Day8().solvePart2(ghostInput)).isEqualTo(6L)
    }


}
