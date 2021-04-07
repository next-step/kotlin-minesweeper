package minesweeper

import minesweeper.domain.BoomPositionMaker
import minesweeper.domain.MineMap
import minesweeper.view.InputView
import minesweeper.view.OutputView

fun main(args: Array<String>) {
    val gameConfig = InputView.inputMineGameConfig()
    val boomPosition = BoomPositionMaker.makePosition(gameConfig)
    OutputView.mineGameStart()
    val mineMap = MineMap(gameConfig, boomPosition)
    OutputView.drawMap(mineMap.getMap())
}
