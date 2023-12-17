package minesweeper.board

import minesweeper.cell.Cell
import minesweeper.position.Position

abstract class GameBoard {

    protected abstract val board: List<List<Cell>>

    abstract fun visit(position: Position)

    abstract fun isExistMinePosition(positions: List<Position>): Boolean

    abstract fun areAllCellsOpened(): Boolean

    operator fun get(index: Int) = this.board[index]

    fun isMine(position: Position): Boolean = board[position.col][position.row].isMine()

    fun isVisited(position: Position): Boolean = board[position.col][position.row].isVisited()

    fun changeCellValue(position: Position, cell: Cell) {
        board[position.col][position.row].changeValue(cell)
    }

    fun getCell(position: Position): Cell = board[position.col][position.row]

    fun render(rowDelimiter: String = "\n", colDelimiter: String = " ") =
        board.joinToString(rowDelimiter) { it.joinToString(colDelimiter) }
}
