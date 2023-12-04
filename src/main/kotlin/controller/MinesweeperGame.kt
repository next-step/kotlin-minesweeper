package controller

import domain.GameBoard
import domain.MineManager
import domain.Position
import view.InputView
import view.OutputView

class MinesweeperGame(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val mineManager: MineManager
) {
    fun startGame() {
        val height = inputView.readHeight()
        val width = inputView.readWidth()
        val mineCount = inputView.readMineCount()

        val gameBoard = GameBoard(mineManager)
        gameBoard.setupBoardAndPlaceMines(height, width, mineCount)

        println("지뢰찾기 게임 시작")

        do {
            val (x, y) = inputView.readCellCoordinates()
            val mineHit = gameBoard.openCell(Position(x, y))

            if (mineHit) {
                outputView.displayGameOverMessage()
                break
            }

            outputView.displayBoard(gameBoard)
        } while (!gameBoard.isGameOver)
    }
}
