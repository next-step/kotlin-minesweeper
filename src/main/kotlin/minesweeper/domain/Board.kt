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
    val grid: List<Points> =
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
}
