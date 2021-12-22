package domain

data class Position(
    val x: Int,
    val y: Int,
) {
    fun getAroundPositions(): List<Position> {
        return AROUND_COORDINATES.mapNotNull { coordinate ->
            val x = x - coordinate[0]
            val y = y - coordinate[1]

            ofNullable(x, y)
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

        fun ofNullable(x: Int, y: Int): Position? {
            if (x < 0 || y < 0) return null
            return Position(x, y)
        }
    }
}
