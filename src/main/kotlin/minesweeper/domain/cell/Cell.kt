package minesweeper.domain.cell

import minesweeper.domain.position.Position

sealed interface Cell {
    val position: Position
    val adjacentPositions: Set<Position>

    data class Mine(
        override val position: Position,
    ) : Cell {
        override val adjacentPositions: Set<Position> = position.adjacentPositions
    }

    data class Clear(
        override val position: Position,
        val mineCount: MineCount = MineCount.ZERO,
        private var isOpened: Boolean = false
    ) : Cell {
        override val adjacentPositions: Set<Position> = position.adjacentPositions
        fun open(): Clear {
            isOpened = true
            return this
        }

        fun isOpened(): Boolean = isOpened

        fun isZeroMineCount(): Boolean = mineCount == MineCount.ZERO
    }

    companion object {
        fun ofClear(position: Position, mineCount: Int): Clear =
            Clear(position, MineCount.from(mineCount))

        fun ofMine(position: Position): Mine = Mine(position)
    }
}
