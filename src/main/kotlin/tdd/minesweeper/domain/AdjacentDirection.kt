package tdd.minesweeper.domain

enum class AdjacentDirection(val dr: Int, val dc: Int) {
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
        fun allAdjacentLocations(location: Location): List<Location> {
            return entries.map { Location(location.row + it.dr, location.col + it.dc) }
        }
    }
}
