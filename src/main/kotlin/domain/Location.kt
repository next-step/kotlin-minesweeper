package domain

data class Location(
    val row: LocationValue,
    val column: LocationValue,
    var closed: Boolean = true
) {
    companion object {
        fun of(row: Int, column: Int): Location {
            return Location(LocationValue(row), LocationValue(column))
        }

        fun isMine(location: Location, cells: Map<Location, Cell>): Boolean {
            return cells.values.find { it.location == location } is Cell.Mine
        }

        fun Location.open() {
            this.closed = false
        }
    }
}

@JvmInline
value class LocationValue(val value: Int)
