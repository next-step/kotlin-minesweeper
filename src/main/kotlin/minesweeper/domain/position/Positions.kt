package minesweeper.domain.position

data class Positions(
    private val positions: List<Position>
) : List<Position> by positions {

    fun havePosition(position: Position): Boolean = positions.contains(position)

    fun havePosition(row: Int, col: Int): Boolean = havePosition(Position(row, col))
}
