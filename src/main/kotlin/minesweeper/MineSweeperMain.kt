package minesweeper

import minesweeper.domain.Length
import minesweeper.domain.MineCount
import minesweeper.domain.MineMap
import minesweeper.domain.MinePosition
import minesweeper.view.InputType
import minesweeper.view.InputView
import minesweeper.view.OutputView
import kotlin.math.min

fun main() {
    val height = Length.of(InputView.inputDataFromConsole(InputType.HEIGHT))
    val width = Length.of(InputView.inputDataFromConsole(InputType.WIDTH))
    val mineCount = MineCount.of(InputView.inputDataFromConsole(InputType.MINE_COUNT))

    val mineMap = MineMap(width, height)
    mineMap.makeMine(mineCount)
    OutputView.showGameStartMessage()
    val minePosition = InputView.inputMinePosition()
    inputMinePosition(minePosition, mineMap)
}

private fun inputMinePosition(minePosition: MinePosition, mineMap : MineMap) {
    mineMap.openMine(minePosition) {
        isDone, nearMineCount ->
        if(isDone) {
            return@openMine
        }
        OutputView.showGameResult(mineMap)
        if(nearMineCount == 0) {
            return@openMine
        }
    }
}
