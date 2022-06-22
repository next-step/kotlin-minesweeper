package minesweeper.domain.board.strategy

import minesweeper.domain.common.NumberSet
import minesweeper.domain.common.PositiveInt
import minesweeper.domain.common.until

class DefaultRandomMineStrategy : MineStrategy {

    override fun getMineIndices(numberOfCells: PositiveInt, numberOfMines: PositiveInt): NumberSet {
        val mineIndices = numberOfCells.toShuffledMineIndices(numberOfMines)
        return NumberSet.of(mineIndices)
    }

    private fun PositiveInt.toShuffledMineIndices(numberOfMines: PositiveInt): List<Int> {
        return (0 until this).shuffled().subList(0, numberOfMines.value)
    }
}
