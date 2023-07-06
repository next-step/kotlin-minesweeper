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
                .map { if (minePoints.contains(it)) MineCell(it) else ClearCell(it) }
                .forEach { cells.add(it) }

            minePoints.flatMap { it.adjacent() }
                .filterNot { it.x == width || it.y == height }
                .forEach { adjacentPoint ->
                    val cell = cells.at(adjacentPoint)
                    cells.add(cell.increase())
                }

            return Board(cells = cells, height = height, width = width)
        }

    }
}
