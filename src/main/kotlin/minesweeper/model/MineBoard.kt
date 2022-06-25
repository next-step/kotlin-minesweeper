package minesweeper.model

class MineBoard(
    val board: List<Cells>
) {
    fun sumOfMineCountIn(surroundingPositions: Set<CellPosition>): Int =
        board.sumOf { it.mineCountIn(surroundingPositions) }

    companion object {
        fun of(boardCreateDto: MineBoardCreateDto): MineBoard {
            val cellPositions = CellPositions.of(boardCreateDto.width, boardCreateDto.height)
            val shuffledCellPositions = cellPositions.generateShuffledPositions()

            val cells = Cells.of(shuffledCellPositions, boardCreateDto.mineCount)
            val sortedCells = cells.generateCellsSortedByPosition()

            return cellsToBoard(sortedCells, boardCreateDto.width, boardCreateDto.height)
        }

        private fun cellsToBoard(cells: Cells, width: Int, height: Int): MineBoard =
            List(height) {
                val startIndex = it * width
                val endIndex = startIndex + width
                cells.take(startIndex, endIndex)
            }.let(::MineBoard)
    }
}
