package minesweeper.controller

import minesweeper.domain.data.PositiveNumber
import minesweeper.domain.game.Board
import minesweeper.domain.game.MineGenerator
import minesweeper.view.InputView
import minesweeper.view.ResultView
import minesweeper.view.toShow

class MineSweeperController(
    private val inputView: InputView = InputView,
    private val resultView: ResultView = ResultView
) {
    fun play() {
        val col = PositiveNumber(inputView.inputHeight())
        val row = PositiveNumber(inputView.inputWidth())
        val count = PositiveNumber(inputView.inputMineCount())

        val mine = MineGenerator.generate(row, col, count)
        val board = Board(row, col, mine)

        resultView.showMine(board.board.toShow())
    }
}
