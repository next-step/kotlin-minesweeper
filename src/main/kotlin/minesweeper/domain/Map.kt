package minesweeper.domain

class Map(val cells: List<Cell>) {
    companion object {

        fun create(height: Int, width: Int, mineCount: Int): Map {
            val totalCellCount = height * width
            val blankCount = totalCellCount - mineCount

            val blankCells = List(blankCount) { Cell.Blank() }
            val mineCell = List(mineCount) { Cell.Mine() }

            val cells = blankCells.plus(mineCell).shuffled()
            val repositionCells = arrange(cells, width)

            return Map(repositionCells)
        }

        private fun arrange(cells: List<Cell>, columSize: Int): List<Cell> =
            cells.chunked(columSize)
                .flatMapIndexed { rowIndex, rowCells ->
                    initPosition(rowCells, rowIndex)
                }

        private fun initPosition(rowCells: List<Cell>, rowIndex: Int): List<Cell> =
            rowCells.mapIndexed { columnIndex, columnCell ->
                when (columnCell) {
                    is Cell.Blank -> Cell.Blank(rowIndex, columnIndex)
                    is Cell.Mine -> Cell.Mine(rowIndex, columnIndex)
                }
            }
    }
}
