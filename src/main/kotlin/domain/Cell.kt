package domain

class Cell private constructor(
    val row: Int,
    val column: Int,
) {
    private var _hasMine: Boolean = false

    val hasMine: Boolean
        get() = _hasMine

    fun addMine() {
        _hasMine = true
    }

    companion object {
        fun create(
            row: Int,
            column: Int,
        ): Cell {
            return Cell(row, column)
        }
    }
}
