package minesweeper

import minesweeper.domain.Length
import minesweeper.domain.MineCount
import minesweeper.domain.MineMap
import minesweeper.domain.MinePosition
import minesweeper.view.InputType
import minesweeper.view.InputView
import minesweeper.view.OutputView

fun main() {
    val height = Length.of(InputView.inputDataFromConsole(InputType.HEIGHT))
    val width = Length.of(InputView.inputDataFromConsole(InputType.WIDTH))
    val mineCount = MineCount.of(InputView.inputDataFromConsole(InputType.MINE_COUNT))

    val mineMap = MineMap(width, height)
    mineMap.makeMine(mineCount)
    OutputView.showGameStartMessage()

    inputOpenPosition(mineMap)
}

fun inputOpenPosition(mineMap : MineMap) {
    val minePosition = InputView.inputOpenPosition()
    openTile(minePosition, mineMap)
}

private fun openTile(position: MinePosition, mineMap : MineMap) {
    mineMap.openTile(position) {
        isLoose ->
        if(isLoose) {
            OutputView.showLoseGame()
            return@openTile
        }
        OutputView.showGameResult(mineMap)
        val sizeOfUncheckedTiles = mineMap.mineMap.sumOf {
            it.sizeOfUnChecked()
        }
        if(mineMap.mineTitleCount?.count == sizeOfUncheckedTiles) {
            OutputView.showWinGame()
            return@openTile
        }
        inputOpenPosition(mineMap)
    }
}
