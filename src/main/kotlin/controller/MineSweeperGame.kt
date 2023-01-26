package controller

import view.InputView

class MineSweeperGame {
    private val inputView = InputView()

    fun run() {
        inputView.getHeight()
        inputView.getWidth()
        inputView.getMineNumber()
    }
}
