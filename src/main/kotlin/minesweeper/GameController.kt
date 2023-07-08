package minesweeper

import minesweeper.domain.GameBoard
import minesweeper.domain.Pin
import minesweeper.view.Inputview
import minesweeper.view.OutputView

fun main() {
    run()
}

fun run() {
    val board = readyBoard()
    readyMine(board)
    playGame(board)
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
        val pin = board.openPin(height, width)
        checkOpenMinePin(pin)
        OutputView.showMineSweeper(board)
        continuable = checkOpenAllNormalPin(board)
    } while (continuable)
}

fun checkOpenAllNormalPin(board: GameBoard): Boolean {
    if (!board.askContinuable()) {
        OutputView.showWin()
        return false
    }
    return true
}

fun checkOpenMinePin(pin: Pin) {
    if (pin.isMinePin()) {
        OutputView.showLose()
        return
    }
}
