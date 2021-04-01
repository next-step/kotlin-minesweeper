package minesweeper.domain

import kotlin.random.Random

class RandomDoubles(private val values: List<Double>) : List<Double> by values {
    constructor(count: Int) : this(valuesOf(count))

    companion object {
        private fun valuesOf(count: Int) = (1..count).map { Random.nextDouble() }
    }
}
