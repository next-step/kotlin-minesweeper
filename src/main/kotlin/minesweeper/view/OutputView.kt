package minesweeper.view

import minesweeper.domain.GameBoard
import minesweeper.domain.NormalPin
import minesweeper.domain.Pin

object OutputView {
    fun showStart() {
        println("지뢰찾기 게임 시작")
    }

    fun showWin() {
        println("Win Game")
    }

    fun showLose() {
        println("Lose Game")
    }

    fun showMineSweeper(gameBoard: GameBoard) {
        for (i in 0 until gameBoard.size.height) {
            showMindSweeperInSameHeight(i, gameBoard)
        }
    }

    private fun showMindSweeperInSameHeight(height: Int, gameBoard: GameBoard) {
        for (j in 0 until gameBoard.size.width) {
            drawPin(gameBoard.getPinAt(height, j))
        }
        println()
    }

    private fun drawPin(pin: Pin) {
        when {
            pin.isOpenable() -> print("C ")
            pin.isMinePin() -> print("* ")
            else -> print((pin as NormalPin).surroundMineNumber.toString() + " ")
        }
    }
}
