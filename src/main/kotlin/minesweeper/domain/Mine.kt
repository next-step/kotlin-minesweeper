package minesweeper.domain

class Mine(private val position: Position) {
    val x: Int
        get() = position.x
    val y: Int
        get() = position.y
}
