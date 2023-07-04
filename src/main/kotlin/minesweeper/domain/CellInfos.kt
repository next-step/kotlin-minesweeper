package minesweeper.domain

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.CellType

data class CellInfos(
    val height: Int,
    val width: Int,
    val cellInfos: List<CellInfo>,
)

data class CellInfo(
    val row: Int,
    val column: Int,
    val cellType: CellType,
) {
    companion object {
        fun from(cell: Cell): CellInfo = CellInfo(
            row = cell.coordinate.row.value,
            column = cell.coordinate.column.value,
            cellType = cell.cellType,
        )
    }
}
