package minesweeper

import minesweeper.domain.MineMapGenerator
import minesweeper.domain.MineMapMeta
import minesweeper.domain.PositionGenerator
import minesweeper.view.InputView
import minesweeper.view.OutputView

object MineSweeper {
    fun drawMap() {
        val mineMapMeta = MineMapMeta(
            height = InputView.getHeight(),
            width = InputView.getWidth(),
            mineCount = InputView.getMineCount()
        )
        val positionGenerator = PositionGenerator(mineMapMeta)
        val minePositions = positionGenerator.generateMinePositions()
        val emptyPositions = positionGenerator.generateEmptyPositions(minePositions)
        val mineMap = MineMapGenerator.generate(minePositions, emptyPositions)

        OutputView.printGameStartMsg()
        OutputView.printMineMap(mineMapMeta, mineMap)
    }
}

fun main() {
    MineSweeper.drawMap()
}
