package minesweeper.model.board.coordinate

data class BoardArea(override val rowCount: PositiveInt, override val columnCount: PositiveInt) : Area {

    override val cellCount: Int
        get() = this.rowCount * this.columnCount

    override val rowRange: IntRange
        get() = 0 until this.rowCount.value

    override val columnRange: IntRange
        get() = 0 until this.columnCount.value

    operator fun get(index: Int): Position = Position(row = index / this.columnCount, column = index % this.columnCount)

    fun indexOf(position: Position): Int = position.row * this.columnCount + position.column

    override fun surroundPositionsOf(position: Position): List<Position> =
        position.surroundPositions.filter { it in this }

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
