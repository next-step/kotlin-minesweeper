package domain

interface CellMapper {
    fun mapToDisplay(cell: Cell): String
}

class DefaultCellMapper : CellMapper {
    override fun mapToDisplay(cell: Cell): String = when (cell) {
        is Cell.Empty -> "C"
        is Cell.MineCell -> "*"
    }
}
