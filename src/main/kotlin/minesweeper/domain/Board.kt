package minesweeper.domain

import java.util.SortedSet

class Board(
    val cells: SortedSet<Cell>,
    val height: Int,
    val width: Int,
    val countOfMine: Int,
) {
    companion object {
        fun of(height: Int, width: Int, countOfMine: Int): Board {

            require(countOfMine <= height * width) { "지뢰는 지도 크기 보다 작아야 합니다." }
            require(countOfMine >= 1) { "최소 한 개 이상의 지뢰가 필요 합니다." }

            val points = Point.square(height, width)
            val minePoints = points.shuffled().take(countOfMine)
            val cells = points
                .map { if (minePoints.contains(it)) MineCell(it) else ClearCell(it) }
                .toSortedSet()
            return Board(cells = cells, height = height, width = width, countOfMine = countOfMine)
        }
    }
}
