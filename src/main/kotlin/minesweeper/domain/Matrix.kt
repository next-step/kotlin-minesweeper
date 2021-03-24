package minesweeper.domain

data class Matrix(val width: Int, val height: Int) {
    init {
        require(width > 0 && height > 0)
    }

    fun around(index: Int): List<Int> {
        return around(toPosition(index))
            .map { toIndex(it) }
    }

    private fun around(position: Position): List<Position> {
        val (x, y) = position

        return Around.aroundPositions(x, y)
            .filter { it.insideOf(width, height) }
    }

    private fun toPosition(index: Int): Position {
        return Position(index % width, index / width)
    }

    fun toIndex(position: Position): Int {
        return position.x + position.y * width
    }

    fun contains(position: Position): Boolean {
        return position.x in (0 until width) && position.y in (0 until height)
    }
}
