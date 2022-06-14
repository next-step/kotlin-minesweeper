package minesweeper.model.map.coordinate

interface Area {
    val width: Int
    val height: Int
    val area: Int
        get() = width * height
}
