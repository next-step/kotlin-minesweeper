package ui

import domain.MinesweeperGame

fun main() {
    val inputView = InputView().also {
        it.show()
    }
    val minesweeperGame = MinesweeperGame(
        width = inputView.width,
        height = inputView.height,
        mineSize = inputView.mineSize
    )
    val minesweeperGameView = MinesweeperGameView()
    minesweeperGameView.printField(minesweeperGame)
}
