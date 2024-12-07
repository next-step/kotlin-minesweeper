package minesweeper

fun main() {
    val height = InputView.getHeight()
    val width = InputView.getWidth()
    val mineCount = InputView.getMineCount()

    val cells = Minesweeper.setUp(height, width)
    val minePositions = Minesweeper.chooseMinePosition(height, width, mineCount)
    val cellsWithMines = cells.placeMines(minePositions)

    OutputView.printStartMessage()
    print(cellsWithMines.print(width))
}