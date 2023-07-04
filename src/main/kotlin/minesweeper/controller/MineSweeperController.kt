package minesweeper.controller

import minesweeper.domain.data.PositiveInt
import minesweeper.domain.game.Board
import minesweeper.view.InputView
import minesweeper.view.ResultView
import minesweeper.view.toShow

class MineSweeperController(
    private val inputView: InputView = InputView,
    private val resultView: ResultView = ResultView
) {
    fun play() {
        val cal = PositiveInt(inputView.inputHeight())
        val row = PositiveInt(inputView.inputWeight())
        val count = PositiveInt(inputView.inputMineCount())

        val board = Board(row, cal, count)

        resultView.showMine(board.cells.toShow())
    }
}
