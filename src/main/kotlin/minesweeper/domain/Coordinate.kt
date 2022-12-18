package minesweeper.domain

class Coordinate(private val type: CoordinateType = CoordinateType.NONE) {

    fun isMine() = type == CoordinateType.MINE
}
