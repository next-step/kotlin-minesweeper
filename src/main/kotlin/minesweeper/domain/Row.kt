package minesweeper.domain

class Row private constructor(
    private val cells: List<Cell>,
) : List<Cell> by cells {
    companion object {
        fun create(cells: List<Cell>): Row {
            return Row(cells)
        }
    }
}
