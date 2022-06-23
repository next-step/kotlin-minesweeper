package minesweeper.model

class MineBoard(
    val board: List<Cells>
) {

    companion object {
        fun of(boardCreateDto: MineBoardCreateDto): MineBoard {
            val cellPositions = CellPositions.of(boardCreateDto.width, boardCreateDto.height)
            cellPositions.shuffle()

            val cells = Cells.of(cellPositions, boardCreateDto.mineCount)
            val sortedCells = cells.generateCellsSortedByPosition()

            return cellsToBoard(sortedCells, boardCreateDto.width, boardCreateDto.height)
        }

        private fun cellsToBoard(cells: Cells, width: Int, height: Int) =
            List(height) {
                val startIndex = it * width
                val endIndex = startIndex + width
                cells.take(startIndex, endIndex)
            }.let(::MineBoard)
    }
}
