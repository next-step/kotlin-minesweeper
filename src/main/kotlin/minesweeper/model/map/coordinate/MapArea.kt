package minesweeper.model.map.coordinate

data class MapArea(override val rowCount: Int, override val columnCount: Int) : Area, Iterable<Position> {

    init {
        require(rowCount > 0)
        require(columnCount > 0)
    }

    operator fun get(index: Int): Position = Position(row = index / this.columnCount, column = index % this.columnCount)
    fun indexOf(position: Position): Int = position.row * this.columnCount + position.column

    override fun iterator() = object : Iterator<Position> {
        private var offset = 0

        override fun hasNext(): Boolean = offset < this@MapArea.cellCount

        override fun next(): Position = this@MapArea[offset++]
    }
}
