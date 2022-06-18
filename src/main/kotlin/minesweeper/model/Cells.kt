package minesweeper.model

class Cells(
    private val cells: List<Cell>
) {
    val size
        get() = cells.size

    val mineCount
        get() = cells.count { it.isMine() }

    override fun toString() = cells.joinToString(CELL_SEPARATOR)

    companion object {
        private const val CELL_SEPARATOR = " "
    }
}
