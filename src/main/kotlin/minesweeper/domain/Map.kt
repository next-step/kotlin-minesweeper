package minesweeper.domain

class Map(val cells: List<Cell>) {
    companion object {

        fun create(meta: MapMeta): Map {
            val totalCellCount = meta.height * meta.width
            val blankCount = totalCellCount - meta.mineCount

            val blankCells = List(blankCount) { Cell.Blank.init() }
            val mineCell = List(meta.mineCount) { Cell.Mine.init() }

            val cells = blankCells.plus(mineCell).shuffled()
            val repositionCells = arrange(cells, meta.width)

            return Map(repositionCells)
        }

        private fun arrange(cells: List<Cell>, columSize: Int): List<Cell> {
            val arrangedCells = cells.chunked(columSize)
                .flatMapIndexed { rowIndex, rowCells ->
                    initPosition(rowCells, rowIndex)
                }

            return initMineCount(arrangedCells)
        }

        private fun initPosition(rowCells: List<Cell>, rowIndex: Int): List<Cell> =
            rowCells.mapIndexed { columnIndex, columnCell ->
                val xPosition = Position(rowIndex)
                val yPosition = Position(columnIndex)

                when (columnCell) {
                    is Cell.Blank -> Cell.Blank(xPosition, yPosition)
                    is Cell.Mine -> Cell.Mine(xPosition, yPosition)
                }
            }

        private fun initMineCount(cells: List<Cell>): List<Cell> =
            cells.map { cell ->
                if (cell is Cell.Mine) return@map cell

                val mineCount = countNearMine(
                    cells = cells,
                    xPosition = cell.xPosition,
                    yPosition = cell.yPosition
                )

                Cell.Blank(cell.xPosition, cell.yPosition, mineCount)
            }

        private fun countNearMine(cells: List<Cell>, xPosition: Position, yPosition: Position): Int {
            val xPositionRange = xPosition.getNear()
            val yPositionRange = yPosition.getNear()

            val findNearCells = xPositionRange.flatMap { nearX ->
                yPositionRange.flatMap { nearY ->
                    cells.filter { cell -> cell.xPosition == nearX && cell.yPosition == nearY }
                }
            }

            return findNearCells.count { cell -> cell is Cell.Mine }
        }
    }
}
