package domain

import domain.geometric.Location

sealed class Cell {
    abstract val location: Location

    data class Mine(override val location: Location) : Cell()
    data class Safe(override val location: Location) : Cell()

    companion object {
        fun safe(location: Location): Cell {
            return Safe(location)
        }

        fun mine(location: Location): Cell {
            return Mine(location)
        }

        fun of(location: Location, miningLocations: List<Location>): Cell {
            return when (location in miningLocations) {
                true -> mine(location)
                false -> safe(location)
            }
        }
    }
}
