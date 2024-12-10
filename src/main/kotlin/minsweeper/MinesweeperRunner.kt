package minsweeper

import minsweeper.view.InputView

class MinesweeperRunner {

    fun run() {
        InputView.showAndGetHeight()
        InputView.showAndGetWidth()
        InputView.showAndGetMineCount()
    }

}

fun main() {
    MinesweeperRunner().run()
}
