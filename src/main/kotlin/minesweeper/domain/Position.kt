package minesweeper.domain

data class Position(
    val y: Int,
    val x: Int
) {
    fun aroundPositions(): List<Position> {
        val rowRange = (y - 1)..(y + 1)
        val colRange = (x - 1)..(x + 1)
        return rowRange.flatMap { row ->
            colRange.map { col ->
                Position(row, col)
            }
        }.filterNot { it == this }
    }
    companion object {
        const val START_INDEX = 1
    }
}
