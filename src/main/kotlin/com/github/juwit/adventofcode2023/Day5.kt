package com.github.juwit.adventofcode2023

class Day5 : Day(5, "If You Give A Seed A Fertilizer") {

    class AlmanachEntry(line: String) {
        val destinationRangeStart: Long
        val sourceRangeStart: Long
        val rangeLength: Long

        init {
            val (destination, source, length) = line.split(" ").map { it.toLong() }
            this.destinationRangeStart = destination
            this.sourceRangeStart = source
            this.rangeLength = length
        }

        // true if value is in source range
        fun contains(value: Long): Boolean{
            return this.sourceRangeStart.rangeTo(this.sourceRangeStart+rangeLength).contains(value)
        }

        // return mapped value for this
        fun next(value: Long): Long {
            val delta = value - sourceRangeStart
            return destinationRangeStart + delta
        }

    }

    class Almanach(input: List<String>) {
        val seeds: Set<Long> = input.first()
            .substringAfter(": ")
            .split(" ")
            .map { it.toLong() }
            .toSet()

        val seedToSoil: Set<AlmanachEntry> = input.subList(input.indexOf("seed-to-soil map:")+1, input.indexOf("soil-to-fertilizer map:"))
            .map { AlmanachEntry(it) }
            .toSet()

        val soilToFertilizer: Set<AlmanachEntry> = input.subList(input.indexOf("soil-to-fertilizer map:")+1, input.indexOf("fertilizer-to-water map:"))
            .map { AlmanachEntry(it) }
            .toSet()

        val fertilizerToWater: Set<AlmanachEntry> = input.subList(input.indexOf("fertilizer-to-water map:")+1, input.indexOf("water-to-light map:"))
            .map { AlmanachEntry(it) }
            .toSet()

        val waterToLight: Set<AlmanachEntry> = input.subList(input.indexOf("water-to-light map:")+1, input.indexOf("light-to-temperature map:"))
            .map { AlmanachEntry(it) }
            .toSet()

        val lightToTemperature: Set<AlmanachEntry> = input.subList(input.indexOf("light-to-temperature map:")+1, input.indexOf("temperature-to-humidity map:"))
            .map { AlmanachEntry(it) }
            .toSet()

        val temperatureToHumidity: Set<AlmanachEntry> = input.subList(input.indexOf("temperature-to-humidity map:")+1, input.indexOf("humidity-to-location map:"))
            .map { AlmanachEntry(it) }
            .toSet()

        val humidityToLocation: Set<AlmanachEntry> = input.subList(input.indexOf("humidity-to-location map:")+1, input.size)
            .map { AlmanachEntry(it) }
            .toSet()

        fun mapSeedsToLocation(): List<Long> {
            return seeds
                .map { seed -> seedToSoil.firstOrNull { it.contains(seed) }?.next(seed) ?: seed }
                .map { seed -> soilToFertilizer.firstOrNull { it.contains(seed) }?.next(seed) ?: seed }
                .map { seed -> fertilizerToWater.firstOrNull { it.contains(seed) }?.next(seed) ?: seed }
                .map { seed -> waterToLight.firstOrNull { it.contains(seed) }?.next(seed) ?: seed }
                .map { seed -> lightToTemperature.firstOrNull { it.contains(seed) }?.next(seed) ?: seed }
                .map { seed -> temperatureToHumidity.firstOrNull { it.contains(seed) }?.next(seed) ?: seed }
                .map { seed -> humidityToLocation.firstOrNull { it.contains(seed) }?.next(seed) ?: seed }
        }
    }

    override fun solvePart1(input: List<String>): Number {
        return Almanach(input).mapSeedsToLocation().min()
    }

    override fun solvePart2(input: List<String>): Number {
        TODO("Not yet implemented")
    }
}
