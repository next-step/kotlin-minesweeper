package minesweeper.model.board.coordinate

data class BoardArea(override val rowCount: Int, override val columnCount: Int) : Area, Iterable<Position> {

    val cellCount = this.rowCount * this.columnCount

    init {
        require(rowCount > 0)
        require(columnCount > 0)
    }

    operator fun get(index: Int): Position = Position(row = index / this.columnCount, column = index % this.columnCount)
    fun indexOf(position: Position): Int = position.row * this.columnCount + position.column

    override fun iterator() = object : Iterator<Position> {
        private var offset = 0

        override fun hasNext(): Boolean = offset < this@BoardArea.cellCount

        override fun next(): Position = this@BoardArea[offset++]
    }
}
