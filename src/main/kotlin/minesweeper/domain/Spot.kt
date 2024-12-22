package minesweeper.domain

abstract class Spot(val coordinate: Coordinate) {
    abstract fun isMine(): Boolean
}
