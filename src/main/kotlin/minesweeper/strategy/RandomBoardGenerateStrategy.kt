package minesweeper.strategy

import minesweeper.domain.block.Block
import minesweeper.domain.block.Cell
import minesweeper.domain.block.Mines
import minesweeper.domain.block.Position

object RandomBoardGenerateStrategy : BoardGenerateStrategy {
    private const val START = 0

    override fun generate(width: Int, height: Int, minesCount: Int): List<Block> {
        val positions = positions(width, height)
        val minesPositions = minesPositions(minesCount, positions)
        return positions.map { minesOrCell(it, minesPositions) }
    }

    private fun positions(width: Int, height: Int): List<Position> =
        (START until width).flatMap { x ->
            (START until height).map { y ->
                Position(x, y)
            }
        }

    private fun minesPositions(minesCount: Int, positions: List<Position>): List<Position> =
        (START until minesCount).map {
            positions.random()
        }

    private fun minesOrCell(positions: Position, minesPositions: List<Position>): Block {
        if (minesPositions.contains(positions)) {
            return Mines(positions)
        }
        return Cell(positions)
    }
}
