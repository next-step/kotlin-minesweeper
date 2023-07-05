package minesweeper.domain

class Row private constructor(
    private val cells: List<Cell>,
) : List<Cell> by cells {

    override fun toString(): String {
        return cells.joinToString(CELLS_SEPARATOR)
    }

    companion object {
        private const val CELLS_SEPARATOR: String = " "
        fun create(cells: List<Cell>): Row {
            return Row(cells)
        }
    }
}
