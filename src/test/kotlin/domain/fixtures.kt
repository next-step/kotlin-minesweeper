package domain

import domain.geometric.Dimension
import domain.geometric.Location
import domain.geometric.LocationValue

fun location(row: Int, column: Int): Location = Location(LocationValue(row), LocationValue(column))

fun locations(vararg value: Pair<Int, Int>): List<Location> = value.toList().map { location(it.first, it.second) }

fun cells(dimension: Dimension): MutableList<Cell> {
    return fill(dimension).map {
        Cell.safe(it)
    }.toMutableList()
}

private fun fill(dimension: Dimension): List<Location> {
    val rows = (0 until dimension.height).toList()
    val columns = (0 until dimension.width).toList()
    return rows.flatMap { row ->
        mapToLocation(columns, row)
    }
}

private fun mapToLocation(
    columns: List<Int>,
    row: Int
): List<Location> = columns.map { col ->
    Location(LocationValue(row), LocationValue(col))
}

class CustomLocationSelector(
    private val preparedLocations: List<Location>
) : LocationSelector {
    override fun select(numberOfSelection: Int, locations: List<Location>): List<Location> {
        require(preparedLocations.size == numberOfSelection)
        return preparedLocations
    }
}
