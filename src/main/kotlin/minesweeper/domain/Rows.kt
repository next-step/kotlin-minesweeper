package minesweeper.domain

class Rows private constructor(
    private val rows: List<Row>
) : List<Row> by rows {

    override fun toString(): String {
        return rows.joinToString(ROWS_SEPARATOR)
    }

    companion object {
        private const val ROWS_SEPARATOR: String = "\n"

        fun create(height: Int, width: Int): Rows {
            val rows = createRows(height, width)
            return Rows(rows)
        }

        private fun createRows(height: Int, width: Int): List<Row> {
            return buildList {
                repeat(height) { rowIndex ->
                    val cells = createCells(width, rowIndex)
                    add(Row.create(cells))
                }
            }
        }

        private fun createCells(width: Int, rowIndex: Int): List<Cell> {
            return buildList {
                repeat(width) { columnIndex ->
                    add(Cell.of(columnIndex, rowIndex))
                }
            }
        }
    }
}
