package minesweeper

import minesweeper.domain.Height
import minesweeper.domain.MineNumber
import minesweeper.domain.Width
import minesweeper.view.InputType
import minesweeper.view.InputView

fun main() {
    val height = Height(InputView.inputData(InputType.HEIGHT))
    val width = Width(InputView.inputData(InputType.WIDTH))
    val mineCount = MineNumber(InputView.inputData(InputType.MINE_COUNT))
}