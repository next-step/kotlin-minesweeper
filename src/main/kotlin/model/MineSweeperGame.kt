package model

class MineSweeperGame(
    rows: Int,
    cols: Int,
    mineCount: Int
) {
    private var cells = Cells(rows, cols, mineCount)

    fun getCells() = cells.cells
}
