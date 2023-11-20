package minesweeper.domain

enum class IndexSquare(val position: Position) {
    LEFT_TOP(Position(-1, 1)),
    TOP(Position(0, 1)),
    RIGHT_TOP(Position(1, 1)),
    LEFT(Position(-1, 0)),
    RIGHT(Position(1, 0)),
    LEFT_BOTTOM(Position(-1, -1)),
    BOTTOM(Position(0, -1)),
    RIGHT_BOTTOM(Position(1, -1));

    companion object {
        fun squareIndex(position: Position, mineSweeperIndexes: MineSweeperIndexes): MineSweeperIndexes {
            val squares = values().filter { indexSquare ->
                (position + indexSquare.position in mineSweeperIndexes.mineSweeperIndexes.map { it.position })
            }.map {
                MineSweeperIndex(position + it.position)
            }
            return MineSweeperIndexes(squares)
        }
    }
}
