package minesweeper.domain

import minesweeper.domain.Position.Companion.getAllPosition
import minesweeper.domain.Position.Companion.getRandomPositions

class MineBoard(
    val mineCells: Map<Position, Cell>,
    private val height: Int,
    private val width: Int,
) {
    fun snapshot() = (0 until height).map { row ->
        (0 until width).map { column ->
            mineCells[Position(row, column)] ?: throw IllegalStateException("해당 지뢰 셀을 찾을 수 없습니다. 위치: (${row},${column})")
        }
    }

    companion object {
        fun createBoard(height: Int, width: Int, mineCount: Int): MineBoard {
            val mineCells = HashMap<Position, Cell>()
            val minePositions = getRandomPositions(height, width, mineCount)
            getAllPosition(height, width)
                .forEach {
                    mineCells[it] = if (minePositions.contains(it)) MineCell(it) else CleanCell(it, minePositions)
                }
            return MineBoard(mineCells, height, width)
        }

    }
}
