package minesweeper.domain

class Coordinate(private val type: CoordinateType = CoordinateType.NONE) {

    fun isMine(): Boolean = type == CoordinateType.MINE
}
