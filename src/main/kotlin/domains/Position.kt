package domains

data class Position private constructor(val x: Int, val y: Int) {

    fun getSurroundingPositions(gameSize: GameSize): Positions {
        return Positions(
            listOf(
                Position(x - 1, y - 1),
                Position(x, y - 1),
                Position(x + 1, y - 1),
                Position(x + 1, y),
                Position(x + 1, y + 1),
                Position(x, y + 1),
                Position(x - 1, y + 1),
                Position(x - 1, y),
            ).filterNot { it.x < 0 || it.y < 0 || it.x >= gameSize.width || it.y >= gameSize.height }
        )
    }

    companion object {
        fun fromUserInput(x: Int, y: Int): Position {
            return Position(x - 1, y - 1)
        }

        fun fromApplication(x: Int, y: Int): Position {
            return Position(x, y)
        }
    }
}
