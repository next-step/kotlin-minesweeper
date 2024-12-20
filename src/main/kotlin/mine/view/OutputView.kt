package mine.view

import mine.domain.Mine
import mine.domain.MineRow
import mine.enums.MineCell

object OutputView {
    const val MINE_SYMBOL = "*"

    fun gameStart(mine: Mine) {
        println("지뢰찾기 게임 시작")
        mine.minesweeper.forEach { row ->
            printMineBoard(row)
        }
    }

    private fun printMineBoard(mineRow: MineRow) {
        val rowString = mineRow.mineCells.joinToString(" ") { it.toDisplayString() }
        println(rowString)
    }

    private fun MineCell.toDisplayString(): String =
        when (this) {
            is MineCell.MINE -> "*"
            is MineCell.Number -> value.toString()
        }
}
