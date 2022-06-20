package minesweeper.model.board

import minesweeper.model.board.coordinate.BoardArea
import minesweeper.model.board.coordinate.Position

class CellBuilder(private val boardArea: BoardArea, private val isMineCell: (Position) -> Boolean) {

    fun createCell(position: Position): Cell {
        if (isMineCell(position)) {
            return Cell.Mine(position)
        }

        return Cell.Safe(position, surroundMineCountOf(position))
    }

    private fun surroundMineCountOf(position: Position) = SurroundMineCount(
        position.surroundPositions
            .filter { it in boardArea }
            .count(isMineCell)
    )
}
