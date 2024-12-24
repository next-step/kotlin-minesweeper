package domain

class Cells(private val initialRows: List<Row>) {
    val _rows: MutableList<Row> = initialRows.toMutableList()
    val rows: List<Row> get() = _rows

    fun addNumberHints(): Cells {
        val updatedRows =
            _rows.mapIndexed { rowIndex, row ->
                row.addNumberHints(rowIndex, this)
            }
        return Cells(updatedRows)
    }

    fun areAllNonMinesOpened(): Boolean {
        return rows.all { it.areAllNonMinesOpened() }
    }

    fun openCell(
        row: Int,
        column: Int,
    ): List<Position> {
        val positionsToOpen = mutableListOf<Position>()

        fun openAt(
            currentRow: Int,
            currentColumn: Int,
        ) {
            val cell = _rows[currentRow][currentColumn]
            if (cell.isOpen) return

            val openedPositions = cell.open(currentRow, currentColumn, this)
            positionsToOpen.addAll(openedPositions)

            if (cell is Cell.Empty) {
                openedPositions.forEach { position ->
                    val (adjRow, adjCol) = position
                    openAt(adjRow, adjCol)
                }
            }
        }

        openAt(row, column)
        return positionsToOpen
    }

    fun isCellMine(
        row: Int,
        column: Int,
    ): Boolean {
        return _rows[row][column].isMine()
    }

    operator fun get(row: Int): Row = _rows[row]

    val size: Int
        get() = _rows.size

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
