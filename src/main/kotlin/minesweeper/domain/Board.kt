package minesweeper.domain

import minesweeper.domain.area.Area
import minesweeper.domain.block.Block
import minesweeper.domain.block.EmptyBlock
import minesweeper.domain.block.MineBlock
import minesweeper.domain.block.Position
import minesweeper.domain.block.strategy.MineBlockGenerateStrategy

@JvmInline
value class Board(val blocks: List<Block>) {

    companion object {
        private const val START = 0

        fun of(area: Area, mineCount: MineCount, mineBlockGenerateStrategy: MineBlockGenerateStrategy): Board {
            val positions = positions(area.width, area.height)
            val minesPositions = mineBlockGenerateStrategy.generate(positions, mineCount.mineCount)
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
            return EmptyBlock(positions, calculateMinesCount(positions, minesPositions))
        }

        private fun calculateMinesCount(position: Position, minesPositions: List<Position>): Int =
            Directions.values()
                .map { directions -> directions.nextCoordinate(position.x, position.y) }
                .filter { it.first >= START && it.second >= START }
                .count { Position(it.first, it.second) in minesPositions }
    }
}
