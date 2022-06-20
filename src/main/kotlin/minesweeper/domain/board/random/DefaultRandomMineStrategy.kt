package minesweeper.domain.board.random

import minesweeper.domain.common.PositiveInt
import minesweeper.domain.common.until

class DefaultRandomMineStrategy : RandomMineStrategy {

    override fun strategy(): (numberOfCells: PositiveInt, numberOfMines: PositiveInt) -> List<Int> {
        return { numberOfCells, numberOfMines ->
            numberOfCells.toShuffledMineIndices(numberOfMines)
        }
    }

    private fun PositiveInt.toShuffledMineIndices(numberOfMines: PositiveInt): List<Int> {
        return (0 until this).shuffled().subList(0, numberOfMines.value)
    }
}
