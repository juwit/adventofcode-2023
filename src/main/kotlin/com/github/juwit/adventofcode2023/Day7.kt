package com.github.juwit.adventofcode2023

class Day7 : Day(7, "Camel Cards") {

    class Hand(input: String, val joker: Boolean = false) : Comparable<Hand> {

        val cards: List<Card>
        val bid: Long
        var type: HandTypes

        init {
            val (cardsStr, bidStr) = input.split(" ")

            cards = cardsStr.asSequence()
                .map { Card.valueOf("CARD_$it") }
                .toList()
            bid = bidStr.toLong()

            // process types
            // group cards by values
            val cardsGrouped = cards.groupBy { it }
                .mapValues { it.value.size }

            if (cardsGrouped.containsValue(5)) {
                type = HandTypes.FIVE_OF_A_KIND
            } else if (cardsGrouped.containsValue(4)) {
                type = HandTypes.FOUR_OF_A_KIND
            } else if (cardsGrouped.containsValue(3) && cardsGrouped.containsValue(2)) {
                type = HandTypes.FULL_HOUSE
            } else if (cardsGrouped.containsValue(3)) {
                type = HandTypes.THREE_OF_A_KIND
            } else if (cardsGrouped.count { it.value == 2 } == 2) {
                type = HandTypes.TWO_PAIR
            } else if (cardsGrouped.any { it.value == 2 }) {
                type = HandTypes.ONE_PAIR
            } else {
                type = HandTypes.HIGH_CARD
            }

            if (joker) {
                val numberOfJokers = cardsGrouped[Card.CARD_J]
                if (numberOfJokers == 4) {
                    type = HandTypes.FIVE_OF_A_KIND
                }
                if (numberOfJokers == 3) {
                    if (cardsGrouped.containsValue(2)) { // one other pair
                        type = HandTypes.FIVE_OF_A_KIND
                    } else { // high card
                        type = HandTypes.FOUR_OF_A_KIND
                    }
                }
                if (numberOfJokers == 2) {
                    if (type == HandTypes.FULL_HOUSE) {
                        type = HandTypes.FIVE_OF_A_KIND
                    }
                    if (type == HandTypes.TWO_PAIR) {
                        type = HandTypes.FOUR_OF_A_KIND
                    }
                    if (type == HandTypes.ONE_PAIR) {
                        type = HandTypes.THREE_OF_A_KIND
                    }
                }
                if (numberOfJokers == 1) {
                    if (type == HandTypes.FOUR_OF_A_KIND) {
                        type = HandTypes.FIVE_OF_A_KIND
                    }
                    if (type == HandTypes.THREE_OF_A_KIND) {
                        type = HandTypes.FOUR_OF_A_KIND
                    }
                    if (type == HandTypes.TWO_PAIR) {
                        type = HandTypes.FULL_HOUSE
                    }
                    if (type == HandTypes.ONE_PAIR) {
                        type = HandTypes.THREE_OF_A_KIND
                    }
                    if (type == HandTypes.HIGH_CARD) {
                        type = HandTypes.ONE_PAIR
                    }
                }
            }

            // 5 jokers => five of a kind
            // 4 jokers => five of a kind (of other value)
            // 3 jokers + one pair => five of a kind
            // 3 jokers + high card => 4 of a kind
            // 2 jokers + full => 5 of a kind
            // 2 jokers + 3 => 5 of a kind
            // 2 jokers + 2 pairs => 4 of a kind
            // 2 jokers + HIGH => 3 of a kind
            // 1 joker + FOUR => FIVE
            // 1 joker + FULL => N/A
            // 1 joker + THREE => FOUR
            // 1 joker + TWO PAIR => FULL
            // 1 joker + 1 PAIR => THREE
            // 1 joker + HIGH => PAIR
        }

        override fun compareTo(other: Hand): Int {
            if (this.type !== other.type) {
                return this.type.compareTo(other.type)
            }
            for (i in 0..4) {
                if (this.cards[i] !== other.cards[i]) {
                    if(! this.joker ) {
                        return this.cards[i].compareTo(other.cards[i])
                    }
                    else {
                        return this.cards[i].jokerOrdinal.compareTo(other.cards[i].jokerOrdinal)
                    }
                }
            }
            return 0
        }
    }

    enum class Card(val jokerOrdinal: Int) {
        CARD_2(1), CARD_3(2), CARD_4(3), CARD_5(4), CARD_6(5), CARD_7(6), CARD_8(7), CARD_9(8), CARD_T(9), CARD_J(0), CARD_Q(10), CARD_K(11), CARD_A(12)
    }

    enum class HandTypes {
        HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND, FIVE_OF_A_KIND
    }

    override fun solvePart1(input: List<String>): Number {
        return input
            .map { Hand(it) }
            .sorted()
            .mapIndexed { rank, hand -> (rank + 1) * hand.bid }
            .sum()
    }

    override fun solvePart2(input: List<String>): Number {
        return input
            .map { Hand(it, joker = true) }
            .sorted()
            .mapIndexed { rank, hand -> (rank + 1) * hand.bid }
            .sum()
    }
}
