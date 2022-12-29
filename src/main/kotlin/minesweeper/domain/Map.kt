package minesweeper.domain

class Map(val cells: List<Cell>) {
    companion object {

        fun create(meta: MapMeta): Map {
            val totalCellCount = meta.height * meta.width
            val blankCount = totalCellCount - meta.mineCount

            val blankCells = List(blankCount) { Cell.Blank() }
            val mineCell = List(meta.mineCount) { Cell.Mine() }

            val cells = blankCells.plus(mineCell).shuffled()
            val repositionCells = arrange(cells, meta.width)

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
