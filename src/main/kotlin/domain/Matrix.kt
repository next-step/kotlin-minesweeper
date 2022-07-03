package domain

class Matrix(
    val dimension: Dimension,
    numberOfMines: MinesCounter,
    locationSelector: LocationSelector = RandomLocationSelector
) {
    val cells: Map<Location, Cell>

    init {
        val locations = MatrixFiller.fill(dimension)
        val miningLocations = locationSelector.select(numberOfMines.mineCount, locations)
        cells = locations.map { getCell(it, miningLocations) }.associateBy {
            it.location
        }.toMap()
    }

    private fun getCell(location: Location, miningLocations: List<Location>): Cell {
        return if (location in miningLocations) {
            Cell.mine(location)
        } else {
            Cell.ground(location)
        }
    }

    private object MatrixFiller {
        fun fill(dimension: Dimension): List<Location> {
            val rows = (0 until dimension.height).toList()
            val columns = (0 until dimension.width).toList()
            return rows.flatMap { row ->
                columns.map { col ->
                    Location(LocationValue(row), LocationValue(col))
                }
            }
        }
    }
}
