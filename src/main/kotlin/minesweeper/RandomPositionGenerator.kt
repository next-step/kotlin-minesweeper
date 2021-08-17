package minesweeper

import kotlin.random.Random

class RandomPositionGenerator: PositionGenerator {

    override fun generate(end: Int): Int {
        return Random.nextInt(0, end)
    }
}