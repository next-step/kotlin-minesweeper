package minesweeper.controller

import minesweeper.domain.Land
import minesweeper.domain.generator.TileGenerator
import minesweeper.domain.tile.Tiles
import minesweeper.domain.tile.strategy.RandomGenerateStrategy
import minesweeper.dto.LandDto
import minesweeper.view.InputView
import minesweeper.view.ResultView

object Controller {
    fun start() {
        play(init())
    }

    private fun init(): Land {
        val height = InputFilter.inputPosition(InputView.INPUT_HEIGHT_MESSAGE)
        ResultView.printLineFeed()
        val width = InputFilter.inputPosition(InputView.INPUT_WIDTH_MESSAGE)
        ResultView.printLineFeed()
        val mineCount = InputFilter.inputMineCount(InputView.INPUT_MINE_COUNT_MESSAGE, height.getCalibratedPosition() * width.getCalibratedPosition())
        ResultView.printLineFeed()

        val tileGenerator = TileGenerator(RandomGenerateStrategy(width, height, mineCount))
        val tiles = Tiles(tileGenerator.generate())
        return Land(width, height, tiles)
    }

    private fun play(land: Land) {
        ResultView.printGameStartMessage()
        while (land.selectTile(InputFilter.inputCoordinate(InputView.INPUT_COORDINATE_MESSAGE))) {
            ResultView.printLand(LandDto(land))
            if (isAllOpened(land)) return
        }
        ResultView.printLoseGameMessage()
    }

    private fun isAllOpened(land: Land): Boolean {
        if (land.isAllOpened()) {
            ResultView.printGameOverMessage()
            return true
        }
        return false
    }
}
