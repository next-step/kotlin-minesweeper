package domain

class Board(
    val rows: List<Row>
) {
    val height = rows.size
    val width = rows.first().width

    init {
        require(rows.all { row -> row.width == width }) { "모든 행의 넓이는 같아야합니다." }
    }

    fun isMine(position: Position): Boolean {
        require(position.y < height) { "Y의 좌표값이 높이보다 작아야합니다." }
        require(position.x < width) { "X 좌표값이 넓이보다 작아야합니다." }

        return rows[position.y].isMine(position.x)
    }

    companion object {
        fun build(
            width: Width,
            height: Height,
            minesPosition: MinesPosition,
        ): Board {
            val heightRange = (0 until height.value)
            val rows = heightRange.map { currentHeight ->
                Row.build(width.value, currentHeight, minesPosition)
            }

            return Board(rows)
        }
    }
}
