package minesweeper.domain

import minesweeper.domain.Position.Companion.getAllPositions
import minesweeper.domain.Position.Companion.getRandomPositions

class MineBoard(
    private val mineCells: Map<Position, Cell>,
    private val height: Int,
    private val width: Int,
) {
    fun snapshot() = (0 until height).map { row ->
        (0 until width).map { column ->
            mineCells[Position(row, column)]
                ?: throw IllegalStateException("해당 지뢰 셀을 찾을 수 없습니다. 위치: (${row},${column})")
        }
    }

    companion object {
        fun createBoard(height: Int, width: Int, mineCount: Int): MineBoard {
            val mineCells: HashMap<Position, Cell> = linkedMapOf(
                *positionToCell(getAllPositions(height, width), getRandomPositions(height, width, mineCount))
            )
            return MineBoard(mineCells, height, width)
        }

        private fun positionToCell(
            allPositions: List<Position>,
            minePositions: List<Position>,
        ) = allPositions.map { it to if (minePositions.contains(it)) MineCell(it) else CleanCell(it, minePositions) }
            .toTypedArray()
    }
}
