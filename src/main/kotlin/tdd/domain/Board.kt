package tdd.domain

class Board(
    val cells: Map<Coordinate, Cell>
) {
    fun open(coordinate: Coordinate) {
        val aroundMineCount = Coordinates.around(coordinate)
            .filter { it in cells }
            .count { cellOf(it).isMine() }

        cellOf(coordinate).open(aroundMineCount)
    }

    private fun cellOf(key: Coordinate): Cell {
        return cells[key] ?: throw IllegalArgumentException("보드에 좌표가 존재하지 않습니다")
    }

    companion object {
        fun of(height: Int, width: Int, mineCount: Int, randomCoordinates: (Int) -> Set<Coordinate>): Board {
            val mineCoordinates = randomCoordinates(mineCount)
            val coordinateToCells = Coordinates.all(height, width).map {
                val cell = if (it in mineCoordinates) Cell(Mine) else Cell()
                it to cell
            }

            return Board(mapOf(*coordinateToCells.toTypedArray()))
        }
    }
}
