package minesweeper.strategy

import minesweeper.domain.block.Block
import minesweeper.domain.block.Cell
import minesweeper.domain.block.Mines
import minesweeper.domain.block.Position

object RandomBoardGenerateStrategy : BoardGenerateStrategy {
    override fun generate(width: Int, height: Int, minesCount: Int): List<Block> {
        val positions: List<Position> = (0 until width).flatMap { x -> (0 until height).map { y -> Position(x, y) } }
        val minesPositions = (0..minesCount).map { positions.random() }
        return positions.map { minesOrCell(minesPositions, it) }
    }

    private fun minesOrCell(minesPositions: List<Position>, it: Position): Block {
        if (minesPositions.contains(it)) {
            return Mines(it)
        }
        return Cell(it)
    }
}
