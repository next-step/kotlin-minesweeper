package minesweeper

import minesweeper.domain.CellFinder
import minesweeper.domain.RandomPositionGenerator
import minesweeper.ui.InputType
import minesweeper.ui.InputView
import minesweeper.ui.ResultView

fun main() {
    val height = InputView.inputSize(InputType.HEIGHT)
    val width = InputView.inputSize(InputType.WIDTH)
    val count = InputView.inputSize(InputType.COUNT)

    val minePositions = RandomPositionGenerator(height, width).generate(count)
    val cellFinder = CellFinder.init(height, width)
    cellFinder.convert(minePositions)

    ResultView.printMines(height, width, cellFinder)
}
