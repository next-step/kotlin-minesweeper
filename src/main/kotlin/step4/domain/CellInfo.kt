package step4.domain

import step4.domain.cell.Cell
import step4.domain.cell.CellType
import step4.domain.coordinate.Coordinate

data class CellInfo(
    val row: Int,
    val column: Int,
    val cellType: CellType,
) {
    companion object {
        fun of(coordinate: Coordinate, cell: Cell): CellInfo = CellInfo(
            row = coordinate.row,
            column = coordinate.column,
            cellType = cell.cellType(),
        )
    }
}

data class CellInfos(
    val height: Int,
    val values: List<CellInfo>,
)
