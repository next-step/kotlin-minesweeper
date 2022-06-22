package minesweeper.domain.board.strategy

import minesweeper.domain.common.NumberSet

class DefaultRandomMineStrategy : MineStrategy {

    override fun getMineIndices(numberOfCells: Int, numberOfMines: Int): NumberSet {
        val mineIndices = numberOfCells.toShuffledMineIndices(numberOfMines)
        return NumberSet.of(mineIndices)
    }

    private fun Int.toShuffledMineIndices(numberOfMines: Int): List<Int> {
        return (0 until this).shuffled().subList(0, numberOfMines)
    }
}
