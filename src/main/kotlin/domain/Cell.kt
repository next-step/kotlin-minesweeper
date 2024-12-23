package domain

data class Cell private constructor(
    val position: Position,
    val hasMine: Boolean,
) {
    fun addMine(): Cell {
        return Cell(position, true)
    }

    companion object {
        fun create(
            row: Int,
            column: Int,
        ): Cell {
            val position = Position(row, column)
            return Cell(position, false)
        }
    }
}
