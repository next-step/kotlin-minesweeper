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
        // TODO positionGenerator를 MineMapGenerator로 주입하면 코드 압축이 가능하지만, 테스트하기 불리해진다. 어떻게 해결할까?
        val positionGenerator = PositionGenerator(mineMapMeta)
        val minePositions = positionGenerator.generateMinePositions()
        val emptyPositions = positionGenerator.generateEmptyPositions(minePositions)
        val mineMap = MineMapGenerator(mineMapMeta).generate(minePositions, emptyPositions)

        OutputView.printGameStartMsg()
        OutputView.printMineMap(mineMapMeta, mineMap)
    }
}

fun main() {
    MineSweeper.drawMap()
}
