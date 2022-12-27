package minesweeper.domain

class Coordinate(val position: Position, private val type: CoordinateType = CoordinateType.NONE) {

    var count: Int = 0
        private set

    fun counting() = count++

    fun isMine(): Boolean = type == CoordinateType.MINE
}
