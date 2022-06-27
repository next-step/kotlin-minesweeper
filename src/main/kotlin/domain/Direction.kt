package domain

enum class Direction(val rowDirection: Int, val columnDirection: Int) {
    TOP(-1, 0),
    TOP_RIGHT(-1, 1),
    RIGHT(0, 1),
    BOTTOM_RIGHT(1, 1),
    BOTTOM(1, 0),
    LEFT(0, -1),
    TOP_LEFT(-1, -1);

    fun getSurroundLocation(location: Location): Location? {
        val row = location.row.value + rowDirection
        val column = location.column.value + columnDirection

        return Location.of(row, column)
    }
}
