package minesweeper.domain

import minesweeper.Direction
import minesweeper.common.Col
import minesweeper.common.Row
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
        row: Row,
        col: Col,
        mines: Mines,
    ): Point {
        val mine = Mine(row, col)
        if (mine in mines) {
            return mine
        }

        return Land(row, col, mines)
    }

    fun open(
        row: Row,
        col: Col,
    ) {
        Direction.entries.map { direction ->
            row + direction.dy to col + direction.dx
        }.filter { (nextRow, nextCol) ->
            isInBoard(
                nextRow,
                nextCol,
            ) && points[nextRow].cols[nextCol] is Land && !(points[nextRow].cols[nextCol] as Land).isOpened()
        }.forEach { (nextRow, nextCol) ->
            (points[nextRow].cols[nextCol] as Land).apply { open() }
        }
    }

    fun existUnopenedLand(): Boolean = points.any { row -> row.cols.any { point -> point is Land && !point.isOpened() } }

    fun isMine(
        row: Row,
        col: Col,
    ): Boolean {
        check(isInBoard(row, col)) { BOARD_OUT_OF_RANGE_EXCEPTION }
        return points[row].cols[col].isMine()
    }

    private fun isInBoard(
        row: Row,
        col: Col,
    ): Boolean = row >= ZERO && col >= ZERO && row < points.size && col < points[0].cols.size

    companion object {
        private const val BOARD_OUT_OF_RANGE_EXCEPTION = "보드내에 있는 좌표가 아닙니다"
    }
}
