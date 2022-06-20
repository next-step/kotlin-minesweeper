package minesweeper.model.board.coordinate

data class BoardArea(override val rowCount: PositiveInt, override val columnCount: PositiveInt) :
    Area,
    Iterable<Position> {

    val cellCount = this.rowCount * this.columnCount

    operator fun get(index: Int): Position = Position(row = index / this.columnCount, column = index % this.columnCount)
    fun indexOf(position: Position): Int = position.row * this.columnCount + position.column

    override fun iterator() = object : Iterator<Position> {
        private var offset = 0

        override fun hasNext(): Boolean = offset < this@BoardArea.cellCount

        override fun next(): Position = this@BoardArea[offset++]
    }

    companion object {
        fun of(rowCount: Int, columnCount: Int) = BoardArea(
            rowCount = PositiveInt(rowCount),
            columnCount = PositiveInt(columnCount)
        )
    }
}
