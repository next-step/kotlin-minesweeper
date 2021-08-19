package minesweeper

import kotlin.random.Random

class RandomPositionGenerator: PositionGenerator {

    override fun generate(end: Int): Int {
        return Random.nextInt(MINIMUM_RANDOM_NUMBER, end)
    }

    companion object {
        const val MINIMUM_RANDOM_NUMBER = 0
    }
}