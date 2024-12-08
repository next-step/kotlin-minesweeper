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
    private val points: List<Points> =
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

    fun open(pointInput: Pair<Int, Int>): List<Land> {
        val openedLands = mutableListOf<Land>()

        openedLands.add(Land(pointInput.first, pointInput.second))

        direction.filter { (directionRow, directionCol) ->
            val nextRow = pointInput.first + directionRow
            val nextCol = pointInput.second + directionCol

            isInBoard(nextRow, nextCol) && !isMine(nextRow, nextCol)
        }.forEach { (directionRow, directionCol) ->
            val nextRow = pointInput.first + directionRow
            val nextCol = pointInput.second + directionCol

            openedLands.add(Land(nextRow, nextCol))
        }

        return openedLands
    }

    fun countAroundMines(
        currentRow: Int,
        currentCol: Int,
    ): Int =
        direction.count { (directionRow, directionCol) ->
            val nextRow = currentRow + directionRow
            val nextCol = currentCol + directionCol

            isInBoard(nextRow, nextCol) && isMine(nextRow, nextCol)
        }

    fun existUnopenedLand(openedLands: Set<Land>): Boolean = points.any { rows -> containAllPoints(rows, openedLands) }

    fun isMine(
        row: Int,
        col: Int,
    ): Boolean {
        check(isInBoard(row, col)) { BOARD_OUT_OF_RANGE_EXCEPTION }
        return points[row].cols[col].isMine()
    }

    private fun containAllPoints(
        row: Points,
        openedLands: Set<Land>,
    ): Boolean = row.cols.any { point -> !point.isMine() && !openedLands.contains(point) }

    private fun isInBoard(
        row: Int,
        col: Int,
    ): Boolean = row >= ZERO && col >= ZERO && row < points.size && col < points[0].cols.size

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
