package minesweeper.domain

abstract class Spot(private val y: Int, private val x: Int) {
    abstract fun isMine(): Boolean
}
