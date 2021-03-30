package minesweeper.domain

import java.util.SortedMap
import java.util.TreeMap

internal class Board private constructor(private var _cells: SortedMap<Position, Cell>) {

    private val mineCount: Int = _cells.values.filter { it.hasMine }.count()

    val cells: Map<Position, Cell>
        get() = this._cells.toMap()

    internal fun expose(position: Position): GameState {
        val cell = _cells[position] ?: throw IllegalArgumentException("cell not exist ")
        if (cell.hasMine) {
            return GameState.LOSE
        }

        val mineCount = cell.expose(findRoundCells(position))
        if (mineCount != 0) {
            return findGameState()
        }

        expose(position.around)
        return findGameState()
    }

    private fun findGameState(): GameState {
        val uncoveredCount = this._cells.values.filter { !it.covered }.count()
        if (this.mineCount == uncoveredCount) {
            return GameState.WIN
        }

        return GameState.RUNNING
    }

    private fun expose(positions: List<Position>) {
        positions.forEach { position ->

            val cell = _cells[position] ?: return@forEach
            if (cell.covered) {
                return@forEach
            }

            if (cell.hasMine) {
                return@forEach
            }

            val count = cell.expose(findRoundCells(position))
            if (count != 0) {
                return@forEach
            }

            expose(position.around)
        }
    }

    private fun findRoundCells(position: Position): List<Cell> {
        return position.around.mapNotNull { this._cells[it] }
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

            return (0 until range.value).shuffled().take(boardSpec.mineCount.value).map {
                val x = it % boardSpec.width.value
                val y = it / boardSpec.width.value
                Position(NaturalNumber(x), NaturalNumber(y))
            }
        }
    }
}
