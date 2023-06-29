package minesweeper.view

import minesweeper.domain.GameBoard

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
            print(gameBoard.getPin(height, j).getMark())
        }
        println()
    }
}
