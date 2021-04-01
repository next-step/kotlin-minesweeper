package minesweeper

const val CELL = "ㅁ"

fun printCells(cells: Cells) {
    (0 until cells.height).forEach {
        printCellLine(cells, it)
    }
}

fun printCellLine(cells: Cells, row: Int) {
    val startIndex = row * cells.width
    val endIndex = (row + 1) * cells.width
    (startIndex until endIndex).forEach {
        printCellView(cells, it)
    }
    println()
}

fun printCellView(cells: Cells, index: Int) {
    val view = if (cells.cells[index].isOpen) cells.getCellValue(index).toString() else CELL
    print("$view ")
}

fun printStart() {
    println("지뢰찾기 게임 시작")
}

fun printLose() {
    println("Lose Game")
}

fun printWin() {
    println("Win Game")
}
