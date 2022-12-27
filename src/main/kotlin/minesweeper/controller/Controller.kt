package minesweeper.controller

import minesweeper.domain.Land
import minesweeper.domain.generator.TileGenerator
import minesweeper.domain.tile.Tiles
import minesweeper.domain.tile.strategy.RandomGenerateStrategy
import minesweeper.view.InputView
import minesweeper.view.ResultView

object Controller {
    fun start() {
        val height = InputFilter.inputPosition(InputView.INPUT_HEIGHT_MESSAGE)
        ResultView.printLineFeed()
        val width = InputFilter.inputPosition(InputView.INPUT_WIDTH_MESSAGE)
        ResultView.printLineFeed()
        val mineCount = InputFilter.inputMineCount(InputView.INPUT_MINE_COUNT_MESSAGE, height.getCalibratedPosition() * width.getCalibratedPosition())
        ResultView.printLineFeed()

        val tileGenerator = TileGenerator(RandomGenerateStrategy(width, height, mineCount))
        val tiles = Tiles(tileGenerator.generate())
        val land = Land(width, tiles)

        ResultView.printGameStartMessage()
        ResultView.printLand(width.getCalibratedPosition(), land.getTiles())
    }
}
