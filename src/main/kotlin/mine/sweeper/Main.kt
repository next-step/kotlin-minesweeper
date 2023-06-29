package mine.sweeper

import mine.sweeper.view.InputView

fun main() {
    val sweeperGame = MineSweeperGame(
        inputHeight = { InputView.getHeight() },
        inputWidth = { InputView.getWidth() },
    )

    sweeperGame.setMines { InputView.getMines() }
    sweeperGame.printEntireMap()
}
