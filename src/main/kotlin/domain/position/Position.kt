package domain.position

data class Position(
    val row: Int,
    val col: Int
) {
    init {
        require(row >= 0 && col >= 0) { "열과 행은 0보다 작을 수 없습니다." }
    }

    fun aroundPosition(): List<Position> {
        return AroundRelativePosition.values()
            .filter { row + it.relativeRow >= 0 && col + it.relativeCol >= 0 }
            .map { Position(this.row + it.relativeRow, this.col + it.relativeCol) }
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
