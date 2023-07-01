package minesweeper.view

import minesweeper.domain.GameBoard
import minesweeper.domain.MinePin
import minesweeper.domain.NormalPin
import minesweeper.domain.Pin

object OutputView {
    fun showMineSweeper(gameBoard: GameBoard) {
        val height = gameBoard.size.height - 1

        for (i in 0..height) {
            showMindSweeperInSameHeight(i, gameBoard)
        }
    }

    private fun showMindSweeperInSameHeight(height: Int, gameBoard: GameBoard) {
        val width = gameBoard.size.width - 1
        for (j in 0..width) {
            drawPin(gameBoard.getPin(height, j))
        }
        println()
    }

    private fun drawPin(pin: Pin) {
        when (pin) {
            is MinePin -> print("* ")
            is NormalPin -> print("C ")
        }
    }
}
