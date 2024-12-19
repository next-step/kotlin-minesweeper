package domain

interface CellMapper {
    fun mapToDisplay(cell: Cell): String
}

class DefaultCellMapper : CellMapper {
    override fun mapToDisplay(cell: Cell): String =
        when (cell) {
            is Cell.Empty -> "0"
            is Cell.MineCell -> "*"
            is Cell.NumberCell -> cell.count.toString()
        }
}
