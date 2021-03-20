package minesweeper.domain

class Cells(val cells: List<Cell>, val width: Int) : List<Cell> by cells {
    val height: Int = size / width

    init {
        require(width > 0 && height > 0)
        requireNotNull(cells.find { it.bomb })
    }
}
