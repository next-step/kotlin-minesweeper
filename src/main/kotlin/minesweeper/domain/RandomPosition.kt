package minesweeper.domain

import kotlin.random.Random

class RandomPosition {
    companion object {
        fun of(rangeX: Int, rangeY: Int) : Pair<Int, Int> {
            val positionX = Random.nextInt(0, rangeX)
            val positionY = Random.nextInt(0, rangeY)
            return positionX to positionY
        }
    }
}