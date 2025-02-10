package mine.view

import mine.domain.MineBoard
import mine.domain.MineRow
import mine.enums.MineCell

object OutputView {
    const val MINE_SYMBOL = "*"
    const val NOT_OPEN_SYMBOL = "C"

    private fun printMineBoard(mineRow: MineRow) {
        val rowString = mineRow.mineCells.joinToString(" ") { toDisplayString(it) }
        println(rowString)
    }

    private fun toDisplayString(mineCell: MineCell): String {
        return when {
            mineCell.isOpen.not() -> NOT_OPEN_SYMBOL
            else -> mineCell.getCellValue()
        }
    }

    fun gameLoseEnd() {
        println("Lose Game.")
    }

    fun gameWinEnd() {
        println("Win Game.")
    }

    private fun MineCell.getCellValue(): String =
        when (this) {
            is MineCell.MINE -> MINE_SYMBOL
            is MineCell.Number -> value.toString()
        }

    fun gameResult(mineBoard: MineBoard) {
        mineBoard.rows.forEach { row ->
            printMineBoard(row)
        }
    }
}
