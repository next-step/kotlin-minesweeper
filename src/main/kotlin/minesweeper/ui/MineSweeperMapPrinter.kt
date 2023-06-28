package minesweeper.ui

import minesweeper.domain.ClearPoint
import minesweeper.domain.Line
import minesweeper.domain.MinePoint
import minesweeper.domain.MineSweeperMap
import minesweeper.domain.Point

object MineSweeperMapPrinter {
    const val MINE_POINT = "*"
    const val NO_MINE_POINT = "C"
    const val BETWEEN_POINT = " "
    fun print(mineSweeperMap: MineSweeperMap) {
        println("지뢰찾기 게임 시작")
        mineSweeperMap.lines.forEach { printLine(it) }
    }

    private fun printLine(line: Line) {
        line.points.forEach {
            printPoint(it)
            print(BETWEEN_POINT)
        }
        println()
    }

    private fun printPoint(point: Point) {
        when (point) {
            is MinePoint -> print(MINE_POINT)
            is ClearPoint -> print(NO_MINE_POINT)
        }
    }
}
