package minesweeper.model

class Cells(
    val cells: List<Cell>
) : List<Cell> by cells {
    val mineCount
        get() = cells.count { it.isMine() }
}
