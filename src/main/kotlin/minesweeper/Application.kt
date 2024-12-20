package minesweeper

import minesweeper.ui.InputView
import minesweeper.ui.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val dimensions = Dimensions(inputView.readWidth(), inputView.readHeight())
    val minePlacer = MinePlacer(dimensions, inputView.readMineCount())
    var state: State = Playing(Board(dimensions, minePlacer.placeMines()))
    resultView.startView()

    while (state is Playing) {
        state = state.toggle(Position.create(inputView.readOpenPosition()))
        resultView.drawBoard(state.displayBoard())
    }

    resultView.resultDraw(state.isWin())
}
