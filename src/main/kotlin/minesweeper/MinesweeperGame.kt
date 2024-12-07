package minesweeper

fun main() {
    val height = InputView.getHeight()
    val width = InputView.getWidth()
    val mineCount = InputView.getMineCount()

    val cells = Minesweeper.setUp(height, width)
    val mineCells = Minesweeper.chooseMineCells(height, width, mineCount)
    val cellsWithMines = cells.placeMines(mineCells)

    OutputView.printStartMessage()
    OutputView.printStats(cellsWithMines, width)
}