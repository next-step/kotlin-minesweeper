package mine.view

import mine.domain.MineRow
import mine.domain.Minesweeper
import mine.enums.MineCell

object OutputView {
    const val MINE_SYMBOL = "*"

    fun gameStart(minesweeper: Minesweeper) {
        println("지뢰찾기 게임 시작")
        minesweeper.mineBoard.forEach { row ->
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
