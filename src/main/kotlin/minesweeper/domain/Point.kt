package minesweeper.domain

class Point(val coordinate: Coordinate, isMine: Boolean = false) : Mine(isMine) {
    var mineCount = 0
        private set

    fun isItCoordinate(coordinate: Coordinate): Boolean = this.coordinate == coordinate

    fun isLastX(lastX: Int): Boolean = coordinate.isX(lastX)

    fun setMineCount(mineCount: Int) {
        this.mineCount = mineCount
    }
}
