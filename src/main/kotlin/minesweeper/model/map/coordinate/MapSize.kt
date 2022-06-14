package minesweeper.model.map.coordinate

data class MapSize(val width: Int, val height: Int) {

    val area: Int = width * height

    init {
        require(width > 0)
        require(height > 0)
    }
}
