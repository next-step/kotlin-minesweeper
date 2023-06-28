package view

import model.MineBoard
import model.MineMark
import model.Position

object OutputView {
    private const val MINE_SYMBOL = "*"
    private const val SAFETY_SYMBOL = "C"

    fun printBoard(board: MineBoard) {
        groupedByY(positionsSortedYAndX(board))
            .forEach { println(lineMarkSymbols(it)) }
    }

    private fun positionsSortedYAndX(board: MineBoard): List<Pair<Position, MineMark>> {
        return board.elements.toList().sortedWith(
            Comparator.comparing<Pair<Position, MineMark>, Int> { it.first.y }.thenBy { it.first.x }
        )
    }

    private fun groupedByY(positions: List<Pair<Position, MineMark>>): Collection<List<Pair<Position, MineMark>>> {
        return positions.groupBy { it.first.y }.values
    }

    private fun lineMarkSymbols(it: List<Pair<Position, MineMark>>) =
        it.joinToString(" ") { element ->
            when (element.second) {
                MineMark.MINE -> MINE_SYMBOL
                MineMark.SAFETY -> SAFETY_SYMBOL
            }
        }
}
