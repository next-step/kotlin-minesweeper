package minesweeper.domain

import kotlin.random.Random

object MineGenerator {

    private const val FIRST_INDEX_POSITION = 0

    fun generate(rangeX: NumberValue, rangeY: NumberValue): MinePosition {
        val x = Random.nextInt(FIRST_INDEX_POSITION, rangeX.value)
        val y = Random.nextInt(FIRST_INDEX_POSITION, rangeY.value)
        return MinePosition.of(x, y)
    }
}
