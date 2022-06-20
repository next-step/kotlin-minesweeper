package minesweeper.model.board

import minesweeper.model.board.coordinate.Area
import minesweeper.model.board.coordinate.BoardArea
import minesweeper.model.board.coordinate.Position

class Board private constructor(private val boardArea: BoardArea, val cells: Cells) : Area by boardArea {

    fun cellsAtRowOrNull(row: Int): Cells? = runCatching {
        require(Position(row, 0) in boardArea)
        Cells(this.cells.filter { it.row == row })
    }.getOrNull()

    companion object {

        fun build(boardArea: BoardArea, isMineCell: (Position) -> Boolean) = Board(
            boardArea = boardArea,
            cells = Cells(boardArea.map { position -> createCell(position, isMineCell(position)) })
        )

        private fun createCell(position: Position, isMineCell: Boolean): Cell {
            if (isMineCell) {
                return Cell.Mine(position)
            }
            return Cell.Safe(position)
        }
    }
}
