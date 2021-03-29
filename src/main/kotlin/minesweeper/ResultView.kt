package minesweeper

const val MINE = "#"
const val CELL = "O"

fun printCells(cells: Cells) {
    (0 until cells.height).forEach {
        printCellLine(cells, it)
    }
}

fun printCellLine(cells: Cells, row: Int) {
    val startIndex = row * cells.width
    val endIndex = (row + 1) * cells.width
    (startIndex until endIndex).forEach {
        printCellView(cells.cells[it])
    }
    println()
}

fun printCellView(cell: Cell) {
    val view = if (cell.isMine) MINE else CELL
    print("$view ")
}
