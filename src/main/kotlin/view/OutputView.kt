package view

import model.CountedMineBoard
import model.MineBoard
import model.Position
import model.minemark.Mine
import model.minemark.MineCount
import model.minemark.MineMark
import model.minemark.Safety

object OutputView {
    private const val MINE_SYMBOL = "*"
    private const val CLEAN_SYMBOL = "C"

    fun printBoard(countedMineBoard: CountedMineBoard) {
        groupedByY(positionsSortedYAndX(countedMineBoard.mineBoard))
            .forEach { line -> printLine(line) }
    }

    private fun printLine(line: List<Pair<Position, MineMark>>) {
        println(lineMarkSymbols(line.map { it.second }))
    }

    private fun positionsSortedYAndX(board: MineBoard): List<Pair<Position, MineMark>> {
        return board.elements.toList().sortedWith(
            Comparator.comparing<Pair<Position, MineMark>, Int> { it.first.y }.thenBy { it.first.x }
        )
    }

    private fun groupedByY(positions: List<Pair<Position, MineMark>>): Collection<List<Pair<Position, MineMark>>> {
        return positions.groupBy { it.first.y }.values
    }

    private fun lineMarkSymbols(marks: List<MineMark>) =
        marks.joinToString(" ") {
            when (it.isOpened) {
                true -> markSymbol(it)
                false -> CLEAN_SYMBOL
            }
        }

    private fun markSymbol(it: MineMark) = when (it) {
        is Mine -> MINE_SYMBOL
        is Safety -> CLEAN_SYMBOL
        is MineCount -> it.count.toString()
    }
}
