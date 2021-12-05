package minesweeper.domain.block.strategy

import minesweeper.domain.block.Position

object RandomMineBlockGenerateStrategy : MineBlockGenerateStrategy {
    private const val START = 0

    override fun execute(positions: List<Position>, minesCount: Int): List<Position> =
        minesPositions(minesCount, positions)

    private fun minesPositions(minesCount: Int, positions: List<Position>): List<Position> =
        (START..minesCount).map { positions.random() }
}
