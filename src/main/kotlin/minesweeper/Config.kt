package minesweeper

import minesweeper.domain.MineGame
import minesweeper.domain.RandomCellsGenerator
import minesweeper.view.InputView
import minesweeper.view.OutputView

object Config {
    fun inputView() = InputView
    fun outputView() = OutputView
    fun mineGame() = MineGame(cellsGenerator())
    fun cellsGenerator() = RandomCellsGenerator
}
