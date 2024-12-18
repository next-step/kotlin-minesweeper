package minesweeper.domain.strategy

import minesweeper.domain.cell.Location

enum class AdjacentLocationDirection(val row: Int, val column: Int) {
    TOP_LEFT(-1, -1),
    TOP(-1, 0),
    TOP_RIGHT(-1, 1),
    LEFT(0, -1),
    RIGHT(0, 1),
    BOTTOM_LEFT(1, -1),
    BOTTOM(1, 0),
    BOTTOM_RIGHT(1, 1),
    ;

    companion object {
        fun allAdjacentLocations(location: Location) =
            entries.map {
                Location(
                    location.row + it.row,
                    location.column + it.column,
                )
            }
    }
}
