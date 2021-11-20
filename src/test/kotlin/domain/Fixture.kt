package domain

class Fixture {
    val board = Board(height, width)
    val selector = object : PositionSelector(height, width) {
        override fun selectMinePositions(number: Int, excludedPosition: Position): List<Position> {
            return listOf(
                Position(1, 4),
                Position(1, 8),
                Position(2, 3),
                Position(2, 5),
                Position(5, 1),
                Position(6, 7),
                Position(7, 3),
                Position(7, 7),
                Position(8, 7),
                Position(8, 10)
            )
        }
    }

    fun renderedBoard(): String = board.joinToString("\n") { row ->
        row.map { if (it.isMine()) "*" else it.mineNumber() }.joinToString(" ")
    }

    companion object {
        private const val height = 10
        private const val width = 10
        const val mineNumber = 10
        val position = Position(1, 1)
    }
}
