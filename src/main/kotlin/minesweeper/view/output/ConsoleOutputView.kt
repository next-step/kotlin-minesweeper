package minesweeper.view.output

import minesweeper.model.map.Cell
import minesweeper.model.map.Cells
import minesweeper.model.map.MineMap

object ConsoleOutputView : OutputView {

    private const val initialMessage = "지뢰찾기 게임 시작"

    override fun printInitialMessage() = println(initialMessage)

    override fun printMap(mineMap: MineMap) {
        println(mineMap.toPrintableString())
    }

    private fun MineMap.toPrintableString(): String {
        val mapArea = this.mapArea
        return (0..mapArea.rowCount).mapNotNull(::cellsAtRowOrNull)
            .joinToString(separator = "\n") { cells -> cells.toPrintableString() }
    }

    private fun Cells.toPrintableString(): String =
        this.joinToString(separator = "") { cell -> cell.toPrintableString() }

    private fun Cell.toPrintableString(): String = when (this) {
        is Cell.Mine -> "*"
        is Cell.Safe -> "C"
    }
}
