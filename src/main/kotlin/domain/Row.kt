package domain

@JvmInline
value class Row(val value: MutableList<Cell>) : MutableList<Cell> by value {
    constructor(vararg cell: Cell) : this(cell.toMutableList())

    companion object {
        fun from(columnCount: Int): Row {
            return Row(
                MutableList(columnCount) {
                    Cell.Land.ZERO
                }
            )
        }
    }
}
