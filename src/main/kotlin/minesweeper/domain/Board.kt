package minesweeper.domain

class Board(
    val cells: Cells,
    val height: Int,
    val width: Int,
) {
    companion object {
        fun of(height: Int, width: Int, countOfMine: Int): Board {

            require(countOfMine <= height * width) { "지뢰는 지도 크기 보다 작아야 합니다." }
            require(countOfMine >= 1) { "최소 한 개 이상의 지뢰가 필요 합니다." }

            val points = Point.square(height, width)
            val minePoints = points.shuffled().take(countOfMine)
            val cells = Cells()

            points
                .map { ClearCell(it) }
                .forEach { cells.add(it) }

            minePoints.forEach { cells.createMine(it) }

            return Board(cells = cells, height = height, width = width)
        }
    }
}
