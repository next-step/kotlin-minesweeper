package minesweeper

import minesweeper.domain.Height
import minesweeper.domain.MineCount
import minesweeper.domain.MineMap
import minesweeper.domain.Width
import minesweeper.view.InputType
import minesweeper.view.InputView
import minesweeper.view.OutputView

fun main() {
    val height = Height.of(InputView.inputDataFromConsole(InputType.HEIGHT))
    val width = Width.of(InputView.inputDataFromConsole(InputType.WIDTH))
    val mineCount = MineCount.of(InputView.inputDataFromConsole(InputType.MINE_COUNT))

    val mineMap = MineMap(width, height)
    mineMap.makeMine(mineCount)
    OutputView.showGameResult(mineMap)
}
