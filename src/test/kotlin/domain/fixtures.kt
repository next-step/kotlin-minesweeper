package domain

import domain.geometric.Location
import domain.geometric.LocationValue

fun location(row: Int, column: Int): Location = Location(LocationValue(row), LocationValue(column))
fun locations(vararg value: Pair<Int, Int>): List<Location> = value.toList().map { location(it.first, it.second) }

class CustomLocationSelector(
    private val preparedLocations: List<Location>
) : LocationSelector {
    override fun select(numberOfSelection: Int, locations: List<Location>): List<Location> {
        require(preparedLocations.size == numberOfSelection)
        return preparedLocations
    }
}
