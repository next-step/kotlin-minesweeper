package minesweeper.domain.cell

import minesweeper.domain.position.CompassDirections
import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions

class CellState(private val value: Int = 0, val cellType: CellType) {
    fun getValue(): Int = value

    companion object {
        fun from(currentPosition: Position, minePositions: Positions): CellState {
            if (minePositions.contains(currentPosition)) {
                return CellState(IS_MINE_VALUE, CellType.IS_MINE)
            }
            val count = countingAdjacentMines(minePositions, currentPosition)
            return CellState(count, CellType.NOT_MINE)
        }

        private fun countingAdjacentMines(minePositions: Positions, currentPosition: Position) =
            CompassDirections.of(currentPosition).map {
                minePositions.contains(it)
            }.count { it }

        private const val IS_MINE_VALUE = -1
    }
}
