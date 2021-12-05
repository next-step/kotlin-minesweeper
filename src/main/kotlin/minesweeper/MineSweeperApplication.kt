package minesweeper

import minesweeper.domain.board.Board
import minesweeper.domain.position.MinePositionGenerator
import minesweeper.domain.position.RandomMinePositionGenerator
import minesweeper.ui.MineSweeperInputView
import minesweeper.ui.MineSweeperOutputView

fun main() = MineSweeperApplication(MineSweeperInputView, MineSweeperOutputView, RandomMinePositionGenerator).run()

class MineSweeperApplication(
    private val inputView: MineSweeperInputView,
    private val outputView: MineSweeperOutputView,
    private val minePositionGenerator: MinePositionGenerator
) {
    fun run() {
        val settings = inputView.settingsInputPrompt()
        val board = Board.of(settings, minePositionGenerator)

        outputView.display(board)
    }
}
