package minesweeper.view.output

import minesweeper.model.map.Cell
import minesweeper.model.map.MineMap
import minesweeper.model.map.coordinate.MapArea

object ConsoleOutputView : OutputView {

    private const val initialMessage = "지뢰찾기 게임 시작"

    override fun printInitialMessage() = println(initialMessage)

    override fun printMap(mineMap: MineMap) = mineMap.print()

    private fun MineMap.print() {
        val cells = this.cells
        cells.forEach { cell -> cell.print(this.mapArea) }
        println()
    }

    private fun Cell.isAtTheEndOfRow(mapArea: MapArea): Boolean = (this.column + 1) % mapArea.columnCount == 0

    private fun Cell.print(mapArea: MapArea) {
        when (this) {
            is Cell.Mine -> print("*")
            is Cell.Safe -> print("C")
        }
        if (this.isAtTheEndOfRow(mapArea)) {
            println()
        }
    }
}
