package minesweeper.domain

abstract class Spot(private val height: FieldHeight, private val width: FieldWidth) {
    abstract fun isMine(): Boolean
}
