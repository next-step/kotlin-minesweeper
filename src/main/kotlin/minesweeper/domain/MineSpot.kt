package minesweeper.domain

class MineSpot(coordinate: Coordinate) : Spot(coordinate) {
    override fun isMine(): Boolean {
        return true
    }
}
