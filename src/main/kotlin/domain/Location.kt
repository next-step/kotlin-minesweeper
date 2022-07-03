package domain

data class Location(
    val row: LocationValue,
    val column: LocationValue,
) {
    companion object {
        fun of(row: Int, column: Int): Location {
            return Location(LocationValue(row), LocationValue(column))
        }

        fun Location.isMine(cells: Map<Location, Cell>): Boolean {
            return cells[this] is Cell.Mine
        }
    }
}

@JvmInline
value class LocationValue(val value: Int)
