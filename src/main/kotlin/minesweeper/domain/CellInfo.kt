package minesweeper.domain

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
            row = cell.row.value,
            column = cell.column.value,
            cellType = cell.cellType,
        )
    }
}
