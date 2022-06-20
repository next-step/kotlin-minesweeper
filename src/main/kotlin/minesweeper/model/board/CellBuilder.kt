package minesweeper.model.board

import minesweeper.model.board.coordinate.BoardArea
import minesweeper.model.board.coordinate.Position

class CellBuilder(private val boardArea: BoardArea, private val isMineCell: (Position) -> Boolean) {

    fun createCell(position: Position): Cell {
        if (isMineCell(position)) {
            return Cell.Mine(position)
        }

        val surroundMineCount = position.surroundPositions
            .filter { it in boardArea }
            .count(isMineCell)

        return Cell.Safe(position, surroundMineCount)
    }
}
