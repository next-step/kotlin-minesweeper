package minesweeper.domain

import minesweeper.common.Col
import minesweeper.common.Row
import minesweeper.config.BoardSize

class Board(
    size: BoardSize,
    val mines: Mines,
) {
    val lands: Lands

    init {
        val grid =
            List(size.height.value * size.width.value) { index ->
                Point(
                    row = index / size.width.value,
                    col = index % size.width.value,
                )
            }

        val elements =
            grid
                .filter { Mine(it) !in mines }
                .map { point ->
                    Land(
                        point = point,
                        aroundMineCount = mines.countAroundMines(point),
                    )
                }

        lands = Lands(elements)
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
