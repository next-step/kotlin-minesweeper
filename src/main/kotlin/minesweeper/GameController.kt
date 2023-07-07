package minesweeper

import minesweeper.domain.GameBoard
import minesweeper.view.Inputview
import minesweeper.view.OutputView

fun main() {
    run()
}

fun run() {
    val board = readyBoard()

    readyMine(board)
    playGame(board)
    showResult(board)
}

fun readyBoard(): GameBoard {
    val height = Inputview.askHeight()
    val width = Inputview.askWidth()
    return GameBoard.ready(height, width)
}

fun readyMine(board: GameBoard) {
    val mineNumber = Inputview.askMineNumber()
    board.repeatPlateMineWithoutDuplication(mineNumber)
    board.closePinAll()
}

fun playGame(board: GameBoard) {
    OutputView.showStart()
    var continuable = true
    do {
        val positions = Inputview.askOpenPosition()
        val height = positions[0]
        val width = positions[1]
        board.openPin(height, width)
        OutputView.showMineSweeper(board)

        continuable = board.askContinuable()
    } while (continuable)
}

fun showResult(board: GameBoard) {
    if (!board.askContinuable()) {
        OutputView.showWin()
        return
    }
    OutputView.showLose()
}
