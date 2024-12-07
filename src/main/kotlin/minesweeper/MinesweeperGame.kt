package minesweeper

fun main() {
    val height = InputView.getHeight()
    val width = InputView.getWidth()
    val mineCount = InputView.getMineCount()

    val minesweeper = Minesweeper.setUp(height, width)
    val cells = minesweeper.cells

    val mineCells = minesweeper.chooseMineCells(mineCount)
    val cellsWithMines = cells.placeMines(mineCells)

    OutputView.printStartMessage()
    OutputView.printStats(cellsWithMines, width)
}
