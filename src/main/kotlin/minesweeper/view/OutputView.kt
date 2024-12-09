package minesweeper.view

import minesweeper.entity.Cell
import minesweeper.entity.Coordinate
import minesweeper.entity.MineField

class OutputView {
    fun printGameStart() {
        println("지뢰찾기 게임을 시작합니다.")
    }

    fun printMineField(mineField: MineField) {
        for (y in 0 until mineField.height.value) {
            for (x in 0 until mineField.width.value) {
                val cell = mineField.findCell(Coordinate(x, y))
                print(if (cell is Cell.Mine) MINE_VIEW else EMPTY_VIEW)
            }
            println()
        }
    }

    companion object {
        private const val MINE_VIEW = "*"
        private const val EMPTY_VIEW = "- "
    }
}
