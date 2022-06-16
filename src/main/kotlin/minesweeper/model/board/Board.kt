package minesweeper.model.board

import minesweeper.model.board.coordinate.Area
import minesweeper.model.board.coordinate.BoardArea
import minesweeper.model.board.coordinate.Position
import minesweeper.utils.toInt

class Board private constructor(val boardArea: BoardArea, val cells: Cells) : Area by boardArea {

    fun cellsAtRowOrNull(row: Int): Cells? = runCatching {
        require(Position(row, 0) in boardArea)
        Cells(this.cells.filter { it.row == row })
    }.getOrNull()

    companion object {

        fun build(boardArea: BoardArea, isMineCell: (Position) -> Boolean) = Board(
            boardArea = boardArea,
            cells = Cells(boardArea.map { position -> createCell(position, isMineCell(position)) })
        )

        fun randomBoard(boardArea: BoardArea, mineCount: Int): Board {
            require(mineCount in 1..boardArea.cellCount)
            val mineIndices = (0 until boardArea.cellCount).shuffled().subList(0, mineCount)
            return build(boardArea) { position -> boardArea.indexOf(position) in mineIndices }
        }

        private fun createCell(position: Position, isMineCell: Boolean): Cell {
            val cellConstructorList = listOf(Cell::Safe, Cell::Mine)
            val constructorIndex = isMineCell.toInt()
            return cellConstructorList[constructorIndex](position)
        }
    }
}
