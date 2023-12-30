package minesweeper.domain.board

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Position

data class MineBoard(
    val cells: Map<Position, Cell>,
) {
    fun open(position: Position): Cell.Clear {
        val cell = getCell(position)
        check(cell is Cell.Clear)
        return cell.open()
    }

    fun isMine(position: Position): Boolean =
        getCell(position) is Cell.Mine

    fun isOpened(position: Position): Boolean {
        val cell = getCell(position)
        check(cell is Cell.Clear)
        return cell.isOpened()
    }

    fun isValidPosition(position: Position): Boolean =
        cells[position] != null

    fun isAllOpened(): Boolean =
        cells.values.none { it is Cell.Clear && it.isOpened().not() }

    private fun getCell(position: Position): Cell = cells[position]
        ?: throw IllegalArgumentException("보드에 정의된 위치가 아닙니다")
}
