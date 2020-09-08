package minesweeper.domain

class Mine(coordinate: Coordinate) : Point(coordinate) {
    override fun isMine(): Boolean? = true

    override fun isOpen(): Boolean = true
}
