package minesweeper.domain

import kotlin.random.Random

private fun Int.toPosition(): Position = Position(this)

class RandomPosition {

    companion object {
        fun of(height: Int, width: Int): MinePosition {
            val randomPositionX = Random.nextInt(0, height).toPosition()
            val randomPositionY = Random.nextInt(0, width).toPosition()
            return MinePosition(randomPositionX, randomPositionY)
        }
    }
}
