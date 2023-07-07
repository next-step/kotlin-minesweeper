package tdd.domain

class Board(
    val cells: Map<Coordinate, Cell>
) {
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
