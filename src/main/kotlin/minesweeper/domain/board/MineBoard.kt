package minesweeper.domain.board

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Position

data class MineBoard(
    val cells: Map<Position, Cell>,
) {
    fun open(position: Position): Cell {
        val cell = cells[position] ?: throw IllegalArgumentException("보드에 정의된 위치가 아닙니다")
        check(cell is Cell.Clear)
        return cell.open()
    }
}
