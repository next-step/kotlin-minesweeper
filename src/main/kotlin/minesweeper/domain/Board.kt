package minesweeper.domain

class Board(
    width: Int,
    height: Int,
) {
    val coordinates = createCoordinate(width, height)
    var cells: List<Cell> = listOf()
        private set

    private fun createCoordinate(
        width: Int,
        height: Int,
    ): List<Coordinate> {
        return (ZERO until height).flatMap { y -> (ZERO until width).map { x -> Coordinate(x, y) } }
    }

    fun makeCell(mineCount: Int = ZERO) {
        val mineCoordinates = getMineCoordinate(mineCount)
        cells = coordinates.map { checkMine(mineCoordinates.contains(it), it) }
    }

    private fun getMineCoordinate(mineCount: Int): List<Coordinate> {
        require(mineCount <= coordinates.size) { "지뢰의 개수가 전체 셀 보다 많을 수 없습니다" }
        return coordinates.shuffled().take(mineCount)
    }

    private fun checkMine(
        isContain: Boolean,
        coordinate: Coordinate,
    ): Cell {
        if (isContain) {
            return Cell(coordinate, true)
        }
        return Cell(coordinate, false)
    }

    fun countMines(): Int {
        return cells.filter { it.hasMine }.size
    }

    fun findCell(
        x: Int,
        y: Int,
    ): Cell {
        return cells.find { it.isItCoordinate(Coordinate(x, y)) } ?: throw IllegalArgumentException("$x, $y 좌표는 없습니다")
    }

    companion object {
        private const val ZERO = 0
    }
}
