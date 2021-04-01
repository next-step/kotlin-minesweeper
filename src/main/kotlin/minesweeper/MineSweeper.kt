package minesweeper

fun main() {
    val width = inputWidth().parseInt()
    val height = inputHeight().parseInt()
    val mineCount = inputMineCount().parseInt()

    val boardGenerator = BoardGenerator(width, height, mineCount)
    val cells = Cells(boardGenerator.generateRandomCell(), width, height)

    printStart()
    while (!cells.isAllOpen()) {
        val index = Location.of(inputPosition()).getConvertIndex(width)
        if (!cells.enterCell(index)) {
            printLose()
            return
        }

        printCells(cells)
    }
    printWin()
}
