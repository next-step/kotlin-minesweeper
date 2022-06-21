package minesweeper.model

class Cells(
    val cells: List<Cell>
) {
    val size
        get() = cells.size

    val mineCount
        get() = cells.count { it.isMine() }
}
