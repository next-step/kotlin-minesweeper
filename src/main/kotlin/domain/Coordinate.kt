package domain

data class Coordinate(
    val row: Int,
    val col: Int,
) {
    companion object {
        const val ROW_START_POSITION = 0
        const val COL_START_POSITION = 0
    }
}
