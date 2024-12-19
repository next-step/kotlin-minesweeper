package domain

class Cells(private val cells: List<Cell>) {
    fun nonMinedCells(): List<Cell> = cells.filter { !it.hasMine }

    // 셀에 지뢰를 추가한 새로운 Cells를 반환
    fun withMineOn(target: Cell): Cells {
        val updatedCells =
            cells.map {
                if (it == target) it.addMine() else it
            }
        return Cells(updatedCells)
    }

    fun allCells(): List<Cell> = cells

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
            return (1..columns).map { column ->
                Cell.create(row, column)
            }
        }
    }
}
