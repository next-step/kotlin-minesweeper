package domains

data class Position private constructor(val x: Int, val y: Int) {

    fun getSurroundingPositions(gameSize: GameSize): Positions {
        return Positions(
            Direction.values().map {
                Position(x + it.dx, y + it.dy)
            }.filterNot { it.x < 0 || it.y < 0 || it.x >= gameSize.width || it.y >= gameSize.height }
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
