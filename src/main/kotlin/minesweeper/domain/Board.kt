package minesweeper.domain

import minesweeper.common.Col
import minesweeper.common.Row

class Board(
    height: Height,
    width: Width,
    val mines: Mines,
) {
    val lands: Lands

    init {
        val grid =
            List(height.value * width.value) { index -> Point(row = index / height.value, col = index % width.value) }
        lands = Lands(grid.filter { Mine(it) !in mines }.map { point -> Land(mines, point) })
    }

    fun canOpen(): Boolean = lands.hasUnopenedLand()

    fun openArea(
        row: Row,
        col: Col,
    ) {
        lands.openSurroundings(Point(row, col))
    }

    fun isMine(
        row: Row,
        col: Col,
    ): Boolean = Mine(Point(row, col)) in mines
}
