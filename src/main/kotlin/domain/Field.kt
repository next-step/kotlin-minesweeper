package domain

class Field(
    val rows: List<Row>
) {
    companion object {
        fun create(height: Int, width: Int, mines: LocationOfMines): Field {
            val rows = (0 until height).map { y ->
                Row(
                    (0 until width).map { x ->
                        Cell.init(x, Coordinate(y, x).toBlock(mines))
                    }
                )
            }

            return Field(rows)
        }
    }
}
