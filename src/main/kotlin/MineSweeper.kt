fun main() {
    val width = inputWidth().parseInt()
    val height = inputHeight().parseInt()
    val mineCount = inputMineCount().parseInt()

    val boardGenerator = BoardGenerator(width, height, mineCount)
    val cells = Cells(boardGenerator.generateRandomCell(), width, height)

    printCells(cells)
}
