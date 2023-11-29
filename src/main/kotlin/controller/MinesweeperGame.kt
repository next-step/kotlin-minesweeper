package controller

import domain.AdjacentMineCounter
import domain.GameBoard
import domain.ShuffledMinePlacer
import inteface.MinePlacementStrategy
import view.InputView
import view.OutputView

class MinesweeperGame(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val minePlacementStrategy: MinePlacementStrategy
) {

    private val minePlacer = ShuffledMinePlacer()
    private val mineCounter = AdjacentMineCounter()

    fun startGame() {
        val height = inputView.readHeight()
        val width = inputView.readWidth()
        val mineCount = inputView.readMineCount()

        val gameBoard = GameBoard(height, width, minePlacementStrategy, minePlacer, mineCounter)
        gameBoard.placeMines(mineCount)
        outputView.displayBoard(gameBoard)
    }
}
