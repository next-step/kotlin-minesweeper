package minesweeper.model.board.coordinate

interface Area : Iterable<Position> {

    val columnCount: PositiveInt
    val rowCount: PositiveInt
    val cellCount: Int
        get() = this.rowCount * this.columnCount

    operator fun get(index: Int): Position = Position(row = index / this.columnCount, column = index % this.columnCount)

    fun indexOf(position: Position): Int = position.row * this.columnCount + position.column

    fun surroundPositionsOf(position: Position): List<Position> =
        position.surroundPositions.filter { it in this }

    override fun iterator() = object : Iterator<Position> {
        private var offset = 0

        override fun hasNext(): Boolean = offset < this@Area.cellCount

        override fun next(): Position = this@Area[offset++]
    }
}
