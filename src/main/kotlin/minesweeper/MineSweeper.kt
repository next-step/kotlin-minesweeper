package minesweeper

import minesweeper.domain.BlockMap
import minesweeper.model.BlockOpenResult

fun main() {
    val height = InputView.height()
    val width = InputView.width()
    val mineCount = InputView.mineCount()
    val blockMap = BlockMap(height, width, mineCount)

    ResultView.printStartGame()
    do {
        val point = InputView.point()
        val result = blockMap.open(point.first, pointY = point.second)
        when (result) {
            BlockOpenResult.MINE -> break
            BlockOpenResult.OPENED -> ResultView.printBlocks(blockMap.blocks)

            BlockOpenResult.ALREADY_OPENED -> ResultView.printAlreadyOpened()
        }
    } while (!blockMap.allOpen())

    if (blockMap.allOpen()) {
        ResultView.printWin()
    } else {
        ResultView.printLose()
    }
}
