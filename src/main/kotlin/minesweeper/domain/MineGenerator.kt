package minesweeper.domain

import minesweeper.common.value.CoordinateValue
import kotlin.random.Random

sealed interface MineGenerator {
    fun generate(height: Int, width: Int): Mine
}

object RandomMineGenerator : MineGenerator {
    override fun generate(height: Int, width: Int): Mine {
        val x = generateRandomCoordinateValue(width)
        val y = generateRandomCoordinateValue(height)
        val position = Position(x, y)
        return Mine(position = position)
    }

    private fun generateRandomCoordinateValue(max: Int): CoordinateValue {
        val randomValue = Random.nextInt(max)
        return CoordinateValue(value = randomValue)
    }
}
