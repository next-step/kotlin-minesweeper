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

    val positionGenerator = RandomPositionGenerator(height, width)
    val initPosition = positionGenerator.generateInit()
    val minePositions = positionGenerator.generate(count)
    val cellFinder = CellFinder(initPosition)
    cellFinder.convert(minePositions)

    ResultView.printMines(height, width, cellFinder)
}
