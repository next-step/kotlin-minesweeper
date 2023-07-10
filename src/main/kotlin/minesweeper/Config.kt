package minesweeper

import minesweeper.domain.MineSweeperGame
import minesweeper.view.InputView
import minesweeper.view.OutputView

object Config {
    fun inputView() = InputView
    fun outputView() = OutputView
    fun mineGame() = MineSweeperGame()
}
