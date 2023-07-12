package minesweeper

import minesweeper.domain.GameBoard
import minesweeper.domain.pin.Pin
import minesweeper.view.Inputview
import minesweeper.view.OutputView
import kotlin.system.exitProcess

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
}

fun playGame(board: GameBoard) {
    OutputView.showStart()
    do {
        val positions = Inputview.askOpenPosition()
        val height = positions[0]
        val width = positions[1]
        val pin = board.openPin(height, width)
        checkOpenMinePin(pin)
        OutputView.showMineSweeper(board)
    } while (isOpenAllNormalPin(board))
}

fun isOpenAllNormalPin(board: GameBoard): Boolean {
    if (board.isNotContinuable()) {
        OutputView.showWin()
        return false
    }
    return true
}

fun checkOpenMinePin(pin: Pin) {
    if (pin.isMinePin()) {
        OutputView.showLose()
        exitProcess(0)
    }
}
