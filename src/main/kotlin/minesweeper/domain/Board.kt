package minesweeper.domain

import java.util.SortedMap
import java.util.TreeMap

internal class Board private constructor(private var _cells: SortedMap<Position, Cell>) {

    val cells: Map<Position, Cell>
        get() = this._cells.toMap()

    internal fun exposeCells() {
        _cells.forEach { (position, cell) ->
            val cells = findRoundCells(position)
            cell.expose(cells)
        }
    }

    private fun findRoundCells(position: Position): List<Cell> {
        return position.getRounds().mapNotNull { _cells[it] }
    }

    companion object {
        internal fun createBoard(
            boardSpec: BoardSpec,
            minePositions: List<Position> = randomMinePositions(boardSpec)
        ): Board {
            val cells: SortedMap<Position, Cell> = TreeMap()
            repeat(boardSpec.height.value) { y ->
                repeat(boardSpec.width.value) { x ->
                    val position = Position(x, y)
                    val hasMine = position in minePositions
                    cells[position] = if (hasMine) MineCell() else EmptyCell()
                }
            }

            return Board(cells)
        }

        private fun randomMinePositions(boardSpec: BoardSpec): List<Position> {
            val range = boardSpec.width * boardSpec.height

            return (0..range.value).shuffled().take(boardSpec.mineCount.value).map {
                val x = it % boardSpec.height.value
                val y = it / boardSpec.height.value
                Position(NaturalNumber(x), NaturalNumber(y))
            }
        }
    }
}
