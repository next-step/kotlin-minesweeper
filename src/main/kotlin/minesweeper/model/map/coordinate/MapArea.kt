package minesweeper.model.map.coordinate

interface AreaSize {
    val width: Int
    val height: Int
}

data class MapArea(override val width: Int, override val height: Int) : AreaSize {

    val area: Int = width * height

    init {
        require(width > 0)
        require(height > 0)
    }

    operator fun get(index: Int): Position = Position(row = index / this.width, column = index % this.width)
    fun indexOf(position: Position): Int = position.row * this.width + position.column
}
