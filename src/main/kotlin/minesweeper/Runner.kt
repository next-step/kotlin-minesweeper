package minesweeper

import minesweeper.domain.BoomPositionMaker
import minesweeper.domain.MineMap
import minesweeper.view.InputView

fun main(args: Array<String>) {
    val gameConfig = InputView.inputMineGameConfig()
    val boomPosition = BoomPositionMaker.makePosition(gameConfig)
    val mineMap = MineMap(gameConfig, boomPosition)

}
