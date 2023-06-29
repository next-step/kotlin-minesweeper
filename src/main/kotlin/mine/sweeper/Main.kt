package mine.sweeper

import mine.sweeper.view.InputView

fun main() {
    val sweeperGame = MineSweeperGame(
        height = InputView.getHeight(),
        width = InputView.getWidth(),
    )

    sweeperGame.setMines(InputView.getMines())
    sweeperGame.printEntireMap()
}
