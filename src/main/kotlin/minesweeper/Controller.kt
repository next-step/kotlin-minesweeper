package minesweeper

import minesweeper.model.board.Board
import minesweeper.view.InputView
import minesweeper.view.OutputView
import minesweeper.view.reder.impl.AdjacentMineCountRenderingStrategy

fun main() {
    val mapHeight: Int = InputView.mapHeight()
    val mapWidth: Int = InputView.mapWidth()
    val minesCount: Int = InputView.countOfMines()
    val board = Board(minesCount, mapHeight, mapWidth)
    val outputView = OutputView(AdjacentMineCountRenderingStrategy(board))
    outputView.printMineMap(board)
}
