package domain

class Cells(private val cells: List<Cell>) {
    fun nonMinedCells(): List<Cell> = cells.filter { !it.hasMine }

    fun updateCell(modifiedCell: Cell): Cells {
        val updatedCells =
            cells.map {
                if (it.position == modifiedCell.position) modifiedCell else it
            }
        return Cells(updatedCells)
    }

    fun allCells(): List<Cell> = cells.toList()

    /**
     * 특정 셀 주변 8칸에 존재하는 지뢰의 개수
     */
    fun countAdjacentMines(cell: Cell): Int {
        val adjacentPositions = cell.position.adjacentPositions()
        return adjacentPositions.count { hasMineAt(it) }
    }

    /**
     * 주어진 위치(좌표)에 지뢰가 있는지 확인
     */
    private fun hasMineAt(position: Position): Boolean {
        return cells.any { it.position == position && it.hasMine }
    }

    companion object {
        fun create(
            rows: Int,
            columns: Int,
        ): Cells {
            val cells =
                (1..rows).flatMap { row ->
                    createRowCells(columns, row)
                }
            return Cells(cells)
        }

        private fun createRowCells(
            columns: Int,
            row: Int,
        ): List<Cell> {
            return List(columns) { index ->
                Cell.create(row, index + 1)
            }
        }
    }
}
