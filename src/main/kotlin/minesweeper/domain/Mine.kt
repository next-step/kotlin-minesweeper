package minesweeper.domain

class Mine(coordinate: Coordinate) : Point(coordinate) {
    override fun isMine(): Boolean? {
        if (isOpen) {
            return true
        }
        return false
    }
}
