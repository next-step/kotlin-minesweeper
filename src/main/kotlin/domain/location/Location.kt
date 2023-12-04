package domain.location

import domain.setting.MineCount
import domain.setting.Size

data class Location(val row: Row, val column: Column) {
    companion object {
        fun makeAtRandom(size: Size) = Location(
            row = Row.makeAtRandom(size.height),
            column = Column.makeAtRandom(size.width),
        )

        fun generateMineLocations(size: Size, mineCount: MineCount): Set<Location> {
            require(size.getPossibleLocationCount() >= mineCount.value) { "number of mines must be less than or equal to number of possible locations" }
            val locations = mutableSetOf<Location>()
            while (locations.size < mineCount.value) {
                locations.add(makeAtRandom(size))
            }
            return locations
        }
    }
}
