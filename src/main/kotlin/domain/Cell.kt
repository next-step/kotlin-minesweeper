package domain

import domain.geometric.Location

sealed class Cell(val location: Location) {

    internal class Mine(location: Location) : Cell(location)
    internal class Safe(location: Location) : Cell(location)

    companion object {
        fun safe(location: Location): Cell {
            return Safe(location)
        }

        fun mine(location: Location): Cell {
            return Mine(location)
        }
    }
}
