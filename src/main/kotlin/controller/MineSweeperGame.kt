package controller

import model.BoardHeight
import model.BoardWidth
import model.MineSize
import view.InputView

class MineSweeperGame {
    private val inputView = InputView()

    fun run() {
        BoardHeight(inputView.getHeight())
        BoardWidth(inputView.getWidth())
        MineSize(inputView.getMineNumber())
    }
}
