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
                LEFT_TOP_POSITION,
                TOP_POSITION,
                RIGHT_TOP_POSITION,
                LEFT_POSITION,
                RIGHT_POSITION,
                LEFT_BOTTOM_POSITION,
                BOTTOM_POSITION,
                RIGHT_BOTTOM_POSITION,
            )

        return directions.map { (dr, dc) ->
            Position(row + dr, column + dc)
        }.filter { it.row > 0 && it.column > 0 }
    }

    companion object {
        val LEFT_TOP_POSITION = -1 to -1
        val TOP_POSITION = -1 to 0
        val RIGHT_TOP_POSITION = -1 to 1
        val LEFT_POSITION = 0 to -1
        val RIGHT_POSITION = 0 to 1
        val LEFT_BOTTOM_POSITION = 1 to -1
        val BOTTOM_POSITION = 1 to 0
        val RIGHT_BOTTOM_POSITION = 1 to 1
    }
}
