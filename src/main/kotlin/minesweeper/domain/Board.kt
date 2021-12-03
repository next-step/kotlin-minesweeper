package minesweeper.domain

import minesweeper.domain.area.Area
import minesweeper.domain.block.Block
import minesweeper.exception.MinesCountOverAreaException
import minesweeper.strategy.BoardGenerateStrategy

@JvmInline
value class Board(val blocks: List<Block>) {

    companion object {
        fun of(area: Area, minesCount: MinesCount, boardGenerateStrategy: BoardGenerateStrategy): Board {
            validateArguments(area, minesCount)
            return Board(boardGenerateStrategy.generate(area.width(), area.height(), minesCount.minesCount).toList())
        }

        private fun validateArguments(area: Area, minesCount: MinesCount) {
            if (area.area() < minesCount.minesCount) {
                throw MinesCountOverAreaException(minesCount.minesCount, area.area())
            }
        }
    }
}
