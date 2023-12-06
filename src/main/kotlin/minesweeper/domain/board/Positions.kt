package minesweeper.domain.board

import minesweeper.domain.cell.Position

data class Positions(
    val allPositions: Set<Position>,
    val minePositions: Set<Position>,
) {
    fun isMine(position: Position): Boolean = position in minePositions
}
