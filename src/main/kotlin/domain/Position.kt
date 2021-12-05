package domain

data class Position(
    val x: Int,
    val y: Int,
) {
    fun getAroundPositions(): List<Position> {
        return AROUND_COORDINATES.map { coordinate ->
            val x = x - coordinate[0]
            val y = y - coordinate[1]

            Position(x, y)
        }
    }

    companion object {
        val AROUND_COORDINATES = listOf(
            arrayOf(-1, -1),
            arrayOf(-1, 0),
            arrayOf(-1, 1),
            arrayOf(0, -1),
            arrayOf(0, 1),
            arrayOf(1, -1),
            arrayOf(1, 0),
            arrayOf(1, 1),
        )
    }
}
