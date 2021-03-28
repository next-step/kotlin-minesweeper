package minesweeper.domain

import java.util.SortedMap
import java.util.TreeMap

internal class Board private constructor(_cells: SortedMap<Position, Cell>) {

    val cells: Map<Position, Cell> = _cells.toSortedMap()

    companion object {
        internal fun createBoard(
            boardSpec: BoardSpec,
            minePositions: List<Position> = randomMinePositions(boardSpec)
        ): Board {
            val cells: SortedMap<Position, Cell> = TreeMap()
            repeat(boardSpec.height.value) { y ->
                repeat(boardSpec.width.value) { x ->
                    val position = Position(x, y)
                    val hasMine = minePositions.contains(position)
                    cells.put(position, if (hasMine) MineCell() else EmptyCell())
                }
            }

            return Board(cells)
        }

        fun randomMinePositions(boardSpec: BoardSpec): List<Position> {
            val range = boardSpec.width * boardSpec.height

            return (0..range.value).shuffled().take(boardSpec.mineCount.value).map {
                val x = it % boardSpec.height.value
                val y = it / boardSpec.height.value
                Position(NaturalNumber(x), NaturalNumber(y))
            }
        }
    }
}
