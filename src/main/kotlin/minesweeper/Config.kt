package minesweeper

import minesweeper.domain.MineSweeperGame
import minesweeper.domain.RandomMinesGenerator
import minesweeper.view.InputView
import minesweeper.view.OutputView

object Config {
    fun inputView() = InputView
    fun outputView() = OutputView
    fun mineGame() = MineSweeperGame(minesGenerator())
    fun minesGenerator() = RandomMinesGenerator
}
