package minesweeper.domain

import minesweeper.domain.point.Land
import minesweeper.domain.point.Mine
import minesweeper.domain.point.Mines
import minesweeper.domain.point.Point

class Board(
    height: Int,
    width: Int,
    mines: Mines,
) {
    val points: List<List<Point>>

    init {
        require(height > ZERO && width > ZERO) { BOARD_INIT_VALUE_EXCEPTION }
        points =
            List(height) { row ->
                List(width) { col -> classifyPoint(row, col, mines) }
            }
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

    companion object {
        private const val ZERO = 0
        private const val BOARD_INIT_VALUE_EXCEPTION = "보드의 높이, 너비는 양수 값이어야 합니다."
    }
}
