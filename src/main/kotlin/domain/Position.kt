package domain

data class Position(
    val row: Int,
    val column: Int,
) {
    /**
     * 해당 좌표의 주변 8칸 좌표 반환
     */
    fun adjacentPositions(): List<Position> {
        val directions =
            listOf(
                -1 to -1,
                -1 to 0,
                -1 to 1,
                0 to -1,
                0 to 1,
                1 to -1,
                1 to 0,
                1 to 1,
            )

        return directions.map { (dr, dc) ->
            Position(row + dr, column + dc)
        }.filter { it.row > 0 && it.column > 0 }
    }
}
