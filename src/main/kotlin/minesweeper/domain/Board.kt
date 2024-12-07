package minesweeper.domain

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
    ): Boolean = points[row].cols[col].isMine()

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
        private val direction =
            listOf(
                -1 to -1,
                -1 to 0,
                -1 to 1,
                0 to -1,
                0 to 1,
                1 to -1,
                1 to 0,
                1 to 1,
            )
    }
}
