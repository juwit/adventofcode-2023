package com.github.juwit.adventofcode2023

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day7Test {

    val exampleInput = """
        32T3K 765
        T55J5 684
        KK677 28
        KTJJT 220
        QQQJA 483
    """.trimIndent().asStringList()

    @Test
    fun shouldParseHand() {
        val hand = Day7.Hand(exampleInput[0])
        assertThat(hand.cards).isEqualTo(
            listOf(
                Day7.Card.CARD_3,
                Day7.Card.CARD_2,
                Day7.Card.CARD_T,
                Day7.Card.CARD_3,
                Day7.Card.CARD_K
            )
        )
        assertThat(hand.bid).isEqualTo(765L)
        assertThat(hand.type).isEqualTo(Day7.HandTypes.ONE_PAIR);
    }

    @Test
    fun shouldComputeHandsTypes(){
        val handTypes = exampleInput.map { Day7.Hand(it) }
            .map { it.type }
        assertThat(handTypes).containsExactly(
            Day7.HandTypes.ONE_PAIR,
            Day7.HandTypes.THREE_OF_A_KIND,
            Day7.HandTypes.TWO_PAIR,
            Day7.HandTypes.TWO_PAIR,
            Day7.HandTypes.THREE_OF_A_KIND
        )
    }


    @Test
    fun shouldParseExampleHands() {
        val hand = Day7.Hand(exampleInput[1])
        assertThat(hand.cards).isEqualTo(
            listOf(
                Day7.Card.CARD_T,
                Day7.Card.CARD_5,
                Day7.Card.CARD_5,
                Day7.Card.CARD_J,
                Day7.Card.CARD_5
            )
        )
        assertThat(hand.bid).isEqualTo(684L)
        assertThat(hand.type).isEqualTo(Day7.HandTypes.THREE_OF_A_KIND);
    }

    @Test
    fun shouldCompareTwoCards(){
        assertThat(Day7.Card.CARD_A > Day7.Card.CARD_K).isTrue()
        assertThat(Day7.Card.CARD_A.compareTo(Day7.Card.CARD_K)).isGreaterThan(0)
    }

    @Test
    fun shouldCompareTwoHandsTypes(){
        assertThat(Day7.HandTypes.FIVE_OF_A_KIND).isGreaterThan(Day7.HandTypes.ONE_PAIR)
    }

    @Test
    fun shouldCompareTwoHands(){
        val hands = exampleInput.map { Day7.Hand(it) }

        val sorted = hands.sorted()

        assertThat(sorted).containsExactly(hands[0], hands[3], hands[2], hands[1], hands[4])
    }

    @Test
    fun shouldSolvePart1(){
        assertThat(Day7().solvePart1(exampleInput)).isEqualTo(6440L)
    }

    @Test
    fun shouldComputeHandsTypesWithJokers(){
        val handTypes = exampleInput.map { Day7.Hand(it, joker = true) }
            .map { it.type }
        assertThat(handTypes).containsExactly(
            Day7.HandTypes.ONE_PAIR,
            Day7.HandTypes.FOUR_OF_A_KIND,
            Day7.HandTypes.TWO_PAIR,
            Day7.HandTypes.FOUR_OF_A_KIND,
            Day7.HandTypes.FOUR_OF_A_KIND
        )
    }

    @Test
    fun shouldSolvePart2(){
        assertThat(Day7().solvePart2(exampleInput)).isEqualTo(5905L)
    }

}
