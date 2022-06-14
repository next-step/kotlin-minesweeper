package minesweeper.view.output

import minesweeper.model.map.Cell
import minesweeper.model.map.MineMap

object ConsoleOutputView : OutputView {

    private const val initialMessage = "지뢰찾기 게임 시작"

    override fun printInitialMessage() = println(initialMessage)

    override fun printMap(mineMap: MineMap) = mineMap.print()

    private fun MineMap.print() {
        this.forEach { cell ->
            cell.print(this)
        }
        println()
    }

    private fun Cell.isAtTheEndOfRow(mineMap: MineMap): Boolean = (this.column + 1) % mineMap.width == 0

    private fun Cell.print(mineMap: MineMap) {
        when (this) {
            is Cell.MineCell -> print("*")
            is Cell.SafeCell -> print("C")
        }
        if (this.isAtTheEndOfRow(mineMap)) {
            println()
        }
    }
}
