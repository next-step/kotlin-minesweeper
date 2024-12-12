package minesweeper.domain

class Cells(
    height: Int,
    width: Int,
) {
    private val cells: MutableList<MutableList<Cell>> = MutableList(height) {
        MutableList(width) { Land() }
    }

    fun toBoard(): List<List<Cell>> {
        return cells.map { it.toList() }
    }

    fun getCell(row: Int, col: Int): Cell {
        return cells[row][col]
    }

    fun setMine(row: Int, col: Int) {
        cells[row][col] = Mine()
    }

    fun getMineCount(): Int {
        return cells.flatten()
            .filterIsInstance<Mine>()
            .size
    }

    fun isAllLandOpened(): Boolean {
        return cells.flatten()
            .filterIsInstance<Land>()
            .none { !it.isOpened }
    }

    fun openAllCellsIncludeMines() {
        cells.flatten()
            .forEach(Cell::open)
    }
}
