package minesweeper.view

import minesweeper.domain.MineMap
import minesweeper.domain.MineMapMeta
import minesweeper.domain.Position
import minesweeper.domain.getStateSymbol

object OutputView {
    fun printGameStartMsg() {
        println("\n지뢰 찾기 게임 시작")
    }

    fun printMineMap(mineMapMeta: MineMapMeta, mineMap: MineMap) {
        for (row in 1 until mineMapMeta.height + 1) {
            printRowCells(mineMapMeta, mineMap, row)
        }
    }

    private fun printRowCells(mineMapMeta: MineMapMeta, mineMap: MineMap, row: Int) {
        for (col in 1 until mineMapMeta.width + 1) {
            print(mineMap.getCell(Position(row, col)).getStateSymbol() + " ")
        }
        println()
    }
}
