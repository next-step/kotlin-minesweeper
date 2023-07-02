package minesweeper.domain

import kotlin.random.Random

private fun Int.toPosition(): Position = Position(this)

class RandomPosition {

    companion object {
        fun of(height: Int, width: Int): MinePosition {
            val randomHeight = Random.nextInt(0, height).toPosition()
            val randomWidth = Random.nextInt(0, width).toPosition()
            return MinePosition(randomHeight, randomWidth)
        }
    }
}
