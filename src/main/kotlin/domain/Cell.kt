package domain

import domain.geometric.Location

sealed interface Cell {
    val location: Location

    companion object {

        fun of(location: Location, miningLocations: List<Location>): Cell {
            return when (location in miningLocations) {
                true -> mine(location)
                false -> safe(location)
            }
        }

        fun safe(location: Location): Cell {
            return Safe(location)
        }

        fun mine(location: Location): Cell {
            return Mine(location)
        }
    }
}

internal data class Mine(override val location: Location) : Cell

internal data class Safe(override val location: Location) : Cell
