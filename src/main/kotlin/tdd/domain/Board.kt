package tdd.domain

class Board(
    val cells: Map<Coordinate, Cell>
) {
    fun open(coordinate: Coordinate) {
        open(ArrayDeque(listOf(coordinate)))
    }

    private tailrec fun open(coordinates: ArrayDeque<Coordinate>, visited: MutableSet<Coordinate> = mutableSetOf()) {
        val coordinate = coordinates.removeFirstOrNull() ?: return
        visited.add(coordinate)
        val cell = cellOf(coordinate)
        val aroundCoordinates = Coordinates.around(coordinate).filter { it in cells }
        val aroundMineCount = aroundCoordinates.count { cellOf(it).isMine() }
        cell.open(aroundMineCount)
        if (cell.isZero()) {
            coordinates.addAll(aroundCoordinates.filterNot { it in visited })
        }

        open(coordinates, visited)
    }

    fun hasMine(coordinate: Coordinate): Boolean = cellOf(coordinate).isMine()

    fun isRunning(): Boolean {
        val closedCount = cells.values.count { !it.isOpened() && !it.isMine() }
        val mineCount = cells.values.count { it.isMine() }
        return closedCount > mineCount
    }

    private fun cellOf(key: Coordinate): Cell {
        return cells[key] ?: throw IllegalArgumentException("보드에 좌표가 존재하지 않습니다")
    }

    companion object {
        fun create(height: Int, width: Int, mineCount: Int, randomCoordinates: (Int) -> Set<Coordinate>): Board {
            val mineCoordinates = randomCoordinates(mineCount)
            val coordinateToCells = Coordinates.all(height, width).map {
                it to Cell.of(isMine = it in mineCoordinates)
            }

            return Board(mapOf(*coordinateToCells.toTypedArray()))
        }
    }
}
