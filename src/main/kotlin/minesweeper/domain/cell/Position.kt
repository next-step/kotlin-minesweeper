package minesweeper.domain.cell

data class Position(
    val x: Int,
    val y: Int,
) {
    init {
        require(x >= 0 && y >= 0) { "property must be zero or positive." }
    }
}

class Positions private constructor(
    private val positions: List<Position>
) : List<Position> by positions {
    companion object {
        fun from(positions: List<Position>): Positions {
            return Positions(positions)
        }
    }
}
