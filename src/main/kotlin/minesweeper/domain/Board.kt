package minesweeper.domain

import minesweeper.common.ZERO
import minesweeper.domain.point.Land
import minesweeper.domain.point.Mine
import minesweeper.domain.point.Mines
import minesweeper.domain.point.Point

class Board(
    height: Height,
    width: Width,
    mines: Mines,
) {
    val points: List<Points> =
        List(height.value) { row ->
            Points(List(width.value) { col -> classifyPoint(row, col, mines) })
        }

    private fun classifyPoint(
        row: Int,
        col: Int,
        mines: Mines,
    ): Point {
        val mine = Mine(row, col)
        if (mine in mines.placedMines) {
            return mine
        }

        return Land(row, col)
    }

    fun isMine(
        row: Int,
        col: Int,
    ): Boolean {
        check(isInBoard(row, col)) { BOARD_OUT_OF_RANGE_EXCEPTION }
        return points[row].cols[col].isMine()
    }

    private fun isInBoard(
        row: Int,
        col: Int,
    ): Boolean = row >= ZERO && col >= ZERO  && row <points.size && col < points[0].cols.size

    fun countAroundMines(
        currentRow: Int,
        currentCol: Int,
    ): Int {
        return direction.count { (directionRow, directionCol) ->
            val nextRow = currentRow + directionRow
            val nextCol = currentCol + directionCol

            nextRow in points.indices && nextCol in points[0].cols.indices && points[nextRow].cols[nextCol].isMine()
        }
    }

    companion object {
        private const val BOARD_OUT_OF_RANGE_EXCEPTION = "보드내에 있는 좌표가 아닙니다"
        private val UP_LEFT = -1 to -1
        private val UP = -1 to 0
        private val UP_RIGHT = -1 to 1
        private val LEFT = 0 to -1
        private val RIGHT = 0 to 1
        private val DOWN_LEFT = 1 to -1
        private val DOWN = 1 to 0
        private val DOWN_RIGHT = 1 to 1

        private val direction = listOf(UP_LEFT, UP, UP_RIGHT, LEFT, RIGHT, DOWN_LEFT, DOWN, DOWN_RIGHT)
    }
}
