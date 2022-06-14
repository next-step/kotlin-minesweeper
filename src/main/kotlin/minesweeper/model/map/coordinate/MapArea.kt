package minesweeper.model.map.coordinate

data class MapArea(override val width: Int, override val height: Int) : Area, Iterable<Position> {

    init {
        require(width > 0)
        require(height > 0)
    }

    operator fun get(index: Int): Position = Position(row = index / this.width, column = index % this.width)
    fun indexOf(position: Position): Int = position.row * this.width + position.column

    override fun iterator(): Iterator<Position> {

        return object : Iterator<Position> {
            private var offset = 0

            override fun hasNext(): Boolean = offset < this@MapArea.area

            override fun next(): Position = this@MapArea[offset++]
        }
    }
}
