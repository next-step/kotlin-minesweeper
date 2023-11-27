package minesweeper

import minesweeper.model.board.Board
import minesweeper.model.board.impl.EvenlyStrategy
import minesweeper.view.InputView
import minesweeper.view.OutputView

fun main() {
    val mapHeight: Int = InputView.mapHeight()
    val mapWidth: Int = InputView.mapWidth()
    val countOfMines: Int = InputView.countOfMines()
    val board: Board = Board(mapHeight, mapWidth, EvenlyStrategy(countOfMines))
    OutputView.printMineMap(board)
}
