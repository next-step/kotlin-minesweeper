package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.Cell

data class BoardDto(val rows: List<RowDto>) {

    constructor(board: Board) : this(board.toRows())
}

data class RowDto(val cells: List<CellDto>)

fun RowDto(cells: List<Cell>): RowDto {
    return RowDto(cells.map(::CellDto))
}

data class CellDto(val mine: Boolean) {

    constructor(cell: Cell) : this(cell.isMine())
}

private fun Board.toRows(): List<RowDto> {
    val cellsSortedByPosition = cells.toSortedMap()
        .toList()
    val cellsGroupByRow = cellsSortedByPosition
        .groupBy(keySelector = { (position, _) -> position.row }, valueTransform = { (_, cell) -> cell })
        .toSortedMap()
        .values
    return cellsGroupByRow.map { RowDto(it) }
}
