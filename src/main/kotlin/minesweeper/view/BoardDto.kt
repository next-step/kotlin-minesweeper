package minesweeper.view

import minesweeper.domain.board.Board
import minesweeper.domain.cell.BlockCell
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.FlagCell
import minesweeper.domain.cell.MineCell

data class BoardDto(val rows: List<RowDto>) {

    constructor(board: Board) : this(board.toRows())
}

data class RowDto(val cells: List<CellDto>)

fun RowDto(cells: List<Cell>): RowDto {
    return RowDto(cells.map(::CellDto))
}

data class CellDto(val mine: Boolean, val flag: Boolean, val aroundMineCount: Int?, val isOpen: Boolean) {

    constructor(cell: Cell) : this(cell.isMine, cell is FlagCell, cell.getAroundMineCount(), cell.isOpen)
}

private fun Board.toRows(): List<RowDto> {
    val cellsSortedByPosition = cells.cells.toSortedMap()
        .toList()
    val cellsGroupByRow = cellsSortedByPosition
        .groupBy(keySelector = { (position, _) -> position.row }, valueTransform = { (_, cell) -> cell })
        .toSortedMap()
        .values
    return cellsGroupByRow.map { RowDto(it) }
}

private fun Cell.getAroundMineCount(): Int? {
    return when(this) {
        is BlockCell -> aroundMineCount.value
        else -> null
    }
}
