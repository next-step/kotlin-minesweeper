package model

data class Coordinates(
    val row: Int,
    val col: Int
) {

    fun getAround(maxRow: Int, maxCol: Int): List<Coordinates> {
        val around = mutableListOf<Coordinates>()
        for (row in row - 1..row + 1) {
            for (col in col - 1..col + 1) {
                if (isNotAvailableRange(row, col, maxRow, maxCol)) continue
                around.add(Coordinates(row, col))
            }
        }
        return around
    }

    private fun isNotAvailableRange(row: Int, col: Int, maxRow: Int, maxCol: Int): Boolean {
        val lessThanMin = row < 0 || col < 0
        val greaterThanMax = row > maxRow || col > maxCol
        return lessThanMin || greaterThanMax
    }
}
