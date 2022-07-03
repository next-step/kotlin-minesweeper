package domain

import domain.Location.Companion.isMine

sealed class Cell(val location: Location) {

    internal class Mine(location: Location) : Cell(location)
    internal class Ground(location: Location) : Cell(location)

    companion object {
        fun ground(location: Location): Cell {
            return Ground(location)
        }

        fun mine(location: Location): Cell {
            return Mine(location)
        }

        fun surroundMineCount(cell: Cell, cells: Map<Location, Cell>): Int {
            return Direction.values().count {
               isMine(it.getSurroundLocation(cell.location), cells)
            }
        }
    }
}
