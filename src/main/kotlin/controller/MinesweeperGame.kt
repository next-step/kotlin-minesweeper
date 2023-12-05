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
        gameBoard.setupBoard(height, width)

        println("지뢰찾기 게임 시작")

        val firstMove = inputView.readCellCoordinates()
        gameBoard.placeMines(mineCount, firstMove)
        handleFirstMove(firstMove, gameBoard)

        while (!gameBoard.isGameOver) {
            val nextMove = inputView.readCellCoordinates()
            handleMove(nextMove, gameBoard)
        }
    }

    private fun handleFirstMove(move: Position, gameBoard: GameBoard) {
        gameBoard.openCellWithoutMineCheck(move)
        outputView.displayBoard(gameBoard)
    }

    private fun handleMove(move: Position, gameBoard: GameBoard) {
        val mineHit = gameBoard.openCell(move)

        if (mineHit) {
            outputView.displayGameOverMessage()
            return
        }

        outputView.displayBoard(gameBoard)
    }
}
