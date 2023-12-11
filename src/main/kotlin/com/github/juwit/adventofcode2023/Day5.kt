package com.github.juwit.adventofcode2023

import java.util.Comparator
import java.util.OptionalLong

class Day5 : Day(5, "If You Give A Seed A Fertilizer") {

    class AlmanachEntry(line: String): Comparable<AlmanachEntry> {
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

        fun containsDestination(value: Long): Boolean {
            return destinationRange().contains(value);
        }

        // return mapped value for this
        fun next(value: Long): Long {
            val delta = value - sourceRangeStart
            return destinationRangeStart + delta
        }

        fun reverse(value: Long): Long {
            val delta = value - destinationRangeStart
            return sourceRangeStart + delta
        }

        fun destinationRange(): LongRange {
            return this.destinationRangeStart.rangeTo(destinationRangeStart + rangeLength)
        }

        override fun compareTo(other: AlmanachEntry): Int {
            return compareValues(this.destinationRangeStart, other.destinationRangeStart)
        }

    }

    class Almanach(input: List<String>) {
        val seeds: List<Long> = input.first()
            .substringAfter(": ")
            .split(" ")
            .map { it.toLong() }

        val seedsFromRanges: List<LongRange> = seeds.chunked(2)
            .map { LongRange(it.first(), it.first() + it.last()) }

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

        fun locationToSeed(): Long {
            // reverse search for the lowest location
            val range = 0..10_000_000_000
            val lowest =  range
                .asSequence()
                .map { location -> humidityToLocation.firstOrNull { it.containsDestination(location) }?.reverse(location) ?: location }
                .map { humidity -> temperatureToHumidity.firstOrNull { it.containsDestination(humidity) }?.reverse(humidity) ?: humidity }
                .map { temperature -> lightToTemperature.firstOrNull { it.containsDestination(temperature) }?.reverse(temperature) ?: temperature }
                .map { light -> waterToLight.firstOrNull { it.containsDestination(light) }?.reverse(light) ?: light }
                .map { water -> fertilizerToWater.firstOrNull { it.containsDestination(water) }?.reverse(water) ?: water}
                .map { fertilize -> soilToFertilizer.firstOrNull { it.containsDestination(fertilize) }?.reverse(fertilize) ?: fertilize }
                .map { soil -> seedToSoil.firstOrNull { it.containsDestination(soil) }?.reverse(soil) ?: soil }
                .mapIndexed { index, seed -> if(seedsFromRanges.any { it.contains(seed) }) index else null }
                .filterNotNull()
                .first()
            return range.elementAt(lowest)
        }

        fun mapSeedsToLocation(seeds: Collection<Long>): List<Long> {
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
        val almanach = Almanach(input)
        return almanach.mapSeedsToLocation(almanach.seeds).min()
    }

    override fun solvePart2(input: List<String>): Number {
        val almanach = Almanach(input)
        return almanach.locationToSeed()
    }
}
