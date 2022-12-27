package minesweeper.model

data class Cell(val x: Int, val y: Int) {
    private operator fun Cell.plus(other: Cell) = Cell(x + other.x, y + other.y)

    fun getNearCells(): List<Cell> {
        return deltaCells.map { deltaCell -> this + deltaCell }
            .filterNot { it == this }
    }

    companion object {
        private const val PREV_INDEX_DIFF = -1
        private const val NEXT_INDEX_DIFF = 1
        private const val HERE_INDEX_DIFF = 0
        private val delta = listOf(PREV_INDEX_DIFF, HERE_INDEX_DIFF, NEXT_INDEX_DIFF)
        private val deltaCells = delta.flatMap { x -> delta.map { y -> Cell(x, y) } }
    }
}
