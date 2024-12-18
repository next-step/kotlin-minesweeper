package domain

class Cell private constructor(
    val position: Position,
) {
    private var _hasMine: Boolean = false

    val hasMine: Boolean
        get() = _hasMine

    fun addMine(): Cell {
        _hasMine = true
        return this
    }

    companion object {
        fun create(
            row: Int,
            column: Int,
        ): Cell {
            val position = Position(row, column)
            return Cell(position)
        }
    }
}
