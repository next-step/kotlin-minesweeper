package minesweeper.domain

class NotMine(coordinate: Coordinate) : Point(coordinate) {
    override fun isOpen(): Boolean = true
}
