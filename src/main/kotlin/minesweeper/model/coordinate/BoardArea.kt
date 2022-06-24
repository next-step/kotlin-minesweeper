package minesweeper.model.coordinate

data class BoardArea(override val rowCount: PositiveInt, override val columnCount: PositiveInt) : Area {

    override val cellCount: Int = this.rowCount * this.columnCount
    override val rowRange: IntRange = 0 until this.rowCount.value
    override val columnRange: IntRange = 0 until this.columnCount.value

    override operator fun get(index: Int): Coordinate =
        Position(row = index / this.columnCount, column = index % this.columnCount)

    fun indexOf(coordinate: Coordinate): Int = coordinate.row * this.columnCount + coordinate.column

    override fun surroundCoordinatesOf(coordinate: Coordinate): List<Coordinate> =
        coordinate.surroundCoordinates.filter { it in this }

    override fun iterator() = object : Iterator<Coordinate> {
        private var offset = 0

        override fun hasNext(): Boolean = offset < this@BoardArea.cellCount

        override fun next(): Coordinate = this@BoardArea[offset++]
    }

    companion object {
        fun of(rowCount: Int, columnCount: Int) = BoardArea(
            rowCount = PositiveInt(rowCount),
            columnCount = PositiveInt(columnCount)
        )
    }
}
