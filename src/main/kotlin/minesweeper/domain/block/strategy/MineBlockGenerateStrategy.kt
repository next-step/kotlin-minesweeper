package minesweeper.domain.block.strategy

import minesweeper.domain.block.Position
import minesweeper.exception.MinesCountOverAreaException

fun interface MineBlockGenerateStrategy {

    fun generate(positions: List<Position>, minesCount: Int): List<Position> {
        validateArguments(positions.size, minesCount)
        return execute(positions, minesCount)
    }

    fun execute(positions: List<Position>, minesCount: Int): List<Position>

    private fun validateArguments(positionsSize: Int, minesCount: Int) {
        if (positionsSize < minesCount) {
            throw MinesCountOverAreaException(minesCount, positionsSize)
        }
    }
}
