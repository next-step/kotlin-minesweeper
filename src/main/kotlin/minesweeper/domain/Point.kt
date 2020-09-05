package minesweeper.domain

abstract class Point(val coordinate: Coordinate) {
    var mineCount = 0
        private set

    fun isItCoordinate(coordinate: Coordinate): Boolean = this.coordinate == coordinate

    fun isLastX(lastX: Int): Boolean = coordinate.isX(lastX)

    fun setMineCount(mineCount: Int) {
        this.mineCount = mineCount
    }

    open fun isMine(): Boolean? = false

    open fun isOpen(): Boolean = false

    open fun open(): Point? = null
}
