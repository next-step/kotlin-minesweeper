package minesweeper.model

class MineBoard(
    val board: List<Cells>
) {

    val mineCount
        get() = board.sumOf { it.mineCount }

    fun sumOfMineCountIn(positions: Set<CellPosition>): Int =
        board.sumOf { it.mineCountIn(positions) }

    fun findClosedCellsIn(positions: Set<CellPosition>): List<Cell> =
        board.flatMap { it.findClosedCellsIn(positions) }

    fun openAndSurroundingNonMineCells(position: CellPosition) =
        board[position.x.position].openAndSurroundingNonMineCells(position.y, this)

    companion object {
        fun of(boardCreateDto: MineBoardCreateDto): MineBoard {
            val cellPositions = CellPositions.of(boardCreateDto.width, boardCreateDto.height)
            val shuffledCellPositions = cellPositions.generateShuffledPositions()

            val cells = Cells.of(shuffledCellPositions, boardCreateDto.mineCount)

            return cellsToBoard(cells, boardCreateDto.width, boardCreateDto.height)
        }

        private fun cellsToBoard(cells: Cells, width: Int, height: Int): MineBoard =
            List(height) {
                val startIndex = it * width
                val endIndex = startIndex + width
                cells.take(startIndex, endIndex)
            }.let(::MineBoard)
    }
}
