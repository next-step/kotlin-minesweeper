package minesweeper.model.map.coordinate

data class MapSize(val width: Int, val height: Int) {

    val area: Int = width * height

    init {
        require(width > 0)
        require(height > 0)
    }

    operator fun get(index: Int): Position = Position(row = index / this.width, column = index % this.width)
    fun indexOf(position: Position): Int = position.row * this.width + position.column
}
