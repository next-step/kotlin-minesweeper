package minesweeper

import minesweeper.model.board.Board
import minesweeper.view.InputView
import minesweeper.view.OutputView

fun main() {
    val mapHeight: Int = InputView.mapHeight()
    val mapWidth: Int = InputView.mapWidth()
    val minesCount: Int = InputView.countOfMines()
    val board: Board = Board(minesCount, mapHeight, mapWidth)
    OutputView.printMineMap(board)
}
