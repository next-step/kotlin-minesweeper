package view

import model.MineBoard
import model.MineMark
import model.Position

object OutputView {
    private const val MINE_SYMBOL = "*"
    private const val SAFETY_SYMBOL = "C"

    fun printBoard(board: MineBoard) {
        board.elements.toList().sortedWith(
            Comparator.comparing<Pair<Position, MineMark>, Int> { it.first.y }.thenBy { it.first.x }
        ).groupBy { it.first.y }
            .values
            .forEach {
                println(
                    it.joinToString(" ") { element ->
                        when (element.second) {
                            MineMark.MINE -> MINE_SYMBOL
                            MineMark.SAFETY -> SAFETY_SYMBOL
                        }
                    }
                )
            }
    }
}
