package minesweeper

import minesweeper.domain.MineCountMapFactory
import minesweeper.domain.MineMap
import minesweeper.domain.MineMapMeta
import minesweeper.domain.OpenState
import minesweeper.domain.Position
import minesweeper.domain.PositionGenerator
import minesweeper.view.InputView
import minesweeper.view.OutputView
import java.util.Stack

object MineSweeper {
    fun drawMap() {
        val mineMapMeta = InputView.readMineMapMeta()
        val mineMap = MineCountMapFactory(PositionGenerator(mineMapMeta)).create()
        OutputView.printGameStartMsg()
        executeGame(mineMapMeta, mineMap)
    }

    private fun executeGame(mineMapMeta: MineMapMeta, mineMap: MineMap) {
        val positionStack = Stack<Position>().apply { addAll(mineMap.values.keys) }
        while (positionStack.isNotEmpty()) {
            val position = positionStack.pop()
            if (mineMap.getCell(position).openState == OpenState.OPENED) continue
            OutputView.printOpenPositionMsg(position)
            if (mineMap.isEmptyCellClicked(position)) {
                OutputView.printMineMap(mineMapMeta, mineMap)
                continue
            }
            OutputView.printGameLoseMsg()
            break
        }
    }
}

fun main() {
    MineSweeper.drawMap()
}
