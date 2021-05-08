package domain.position

data class Position(
    val row: Int,
    val col: Int
) {
    init {
        require(row >= 0 && col >= 0) { "열과 행은 0보다 작을 수 없습니다." }
    }

    fun aroundPositions(): List<Position> {
        return AroundRelativePosition.values()
            .filter { validateAroundPosition(it) }
            .map { Position(this.row + it.relativeRow, this.col + it.relativeCol) }
    }

    private fun validateAroundPosition(aroundRelativePosition: AroundRelativePosition) =
        row + aroundRelativePosition.relativeRow >= 0 && col + aroundRelativePosition.relativeCol >= 0

    fun isInBoard(height: Int, width: Int): Boolean {
        return row < height && col < width
    }

    private enum class AroundRelativePosition(
        val relativeRow: Int,
        val relativeCol: Int
    ) {
        UP(-1, 0),
        DOWN(1, 0),
        RIGHT(0, 1),
        LEFT(0, -1),

        UP_RIGHT(-1, 1),
        UP_LEFT(-1, -1),
        DOWN_RIGHT(1, 1),
        DOWN_LEFT(1, -1),
    }
}
