package domain

import domain.geometric.Dimension
import domain.geometric.Location
import domain.geometric.LocationValue

class Matrix(
    private val dimension: Dimension,
    numberOfMines: NumberOfMines,
    locationSelector: LocationSelector = RandomLocationSelector
) {
    val cells: Map<Location, Cell>

    init {
        val locations = MatrixFiller.fill(dimension)
        val miningLocations = locationSelector.select(numberOfMines.value, locations)
        cells = locations.map {
            when (it) {
                in miningLocations -> Cell.mine(it)
                else -> Cell.safe(it)
            }
        }.associateBy {
            it.location
        }.toMap()
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
