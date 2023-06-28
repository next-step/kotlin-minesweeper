package minesweeper.domain

class Board(
    private val cells: List<Cell>,
    val width: Int,
    val height: Int,
    val countOfMine: Int,
) {
    fun cell(x: Int, y: Int): Cell = cells.find { it.point == Point(x, y) } ?: throw RuntimeException()
    companion object {
        fun of(width: Int, height: Int, countOfMine: Int): Board {

            require(countOfMine <= height * width) { println("지뢰는 지도 크기 보다 작아야 합니다.") }
            require(countOfMine >= 1) { println("최소 한 개 이상의 지뢰가 필요 합니다.") }

            val generator = CellGenerator(RandomBasedMineCellSelector(height, width, countOfMine))

            val points: List<Point> = (0 until height).map { y ->
                (0 until width).map { x -> Point(x, y) }
            }.flatten()

            val cells = points.map { generator.generate(it) }

            return Board(cells, height, width, countOfMine)
        }
    }
}
