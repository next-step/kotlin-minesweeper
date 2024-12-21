package domain

class Cells(private val rows: List<Row>) {
    fun getRows(): List<Row> = rows.toList()

    fun addNumberHints(): Cells {
        val updatedRows =
            rows.mapIndexed { rowIndex, row ->
                row.addNumberHints(rowIndex, this)
            }
        return Cells(updatedRows)
    }

    operator fun get(row: Int): Row = rows[row]

    val size: Int
        get() = rows.size

    companion object {
        fun create(
            height: Int,
            width: Int,
            minePositions: Set<Position>,
        ): Cells {
            val rows =
                List(height) { row ->
                    Row.create(width) { col ->
                        val isMine = minePositions.contains(Position(row, col))
                        Cell.create(isMine)
                    }
                }
            return Cells(rows)
        }
    }
}
