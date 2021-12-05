package minesweeper.domain

import minesweeper.domain.area.Area
import minesweeper.domain.block.Block
import minesweeper.domain.block.Cell
import minesweeper.domain.block.MineBlock
import minesweeper.domain.block.Position
import minesweeper.domain.block.strategy.MineBlockGenerateStrategy

@JvmInline
value class Board(val blocks: List<Block>) {

    companion object {
        private const val START = 0

        fun of(area: Area, minesCount: MinesCount, mineBlockGenerateStrategy: MineBlockGenerateStrategy): Board {
            val positions = positions(area.width, area.height)
            val minesPositions = mineBlockGenerateStrategy.generate(positions, minesCount.minesCount)
            return Board(positions.map { minesOrCell(it, minesPositions) })
        }

        private fun positions(width: Int, height: Int): List<Position> =
            (START until width).flatMap { x ->
                (START until height).map { y ->
                    Position(x, y)
                }
            }

        private fun minesOrCell(positions: Position, minesPositions: List<Position>): Block {
            if (minesPositions.contains(positions)) {
                return MineBlock(positions)
            }
            return Cell(positions)
        }
    }
}
