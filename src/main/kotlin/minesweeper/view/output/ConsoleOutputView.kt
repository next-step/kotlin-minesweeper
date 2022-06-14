package minesweeper.view.output

import minesweeper.model.map.Cell
import minesweeper.model.map.MineMap

object ConsoleOutputView : OutputView {

    private const val initialMessage = "지뢰찾기 게임 시작"

    override fun printInitialMessage() = println(initialMessage)

    override fun printMap(mineMap: MineMap) = mineMap.print()

    private fun MineMap.print() {
        this.forEach { cell ->
            val isEndOfRow = (cell.position.column + 1) % mapSize.width == 0
            cell.print(isEndOfRow)
        }
        println()
    }

    private fun Cell.print(isEdgeOfRow: Boolean = false) {
        when (this) {
            is Cell.MineCell -> print("*")
            is Cell.SafeCell -> print("C")
        }
        if (isEdgeOfRow) {
            println()
        }
    }
}
