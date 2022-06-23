package minesweeper.model

class MineBoard private constructor(
    val board: List<Cells>
) {

    companion object {
        fun of(boardCreateDto: MineBoardCreateDto): MineBoard {
            val cellPositions = CellPositions.of(boardCreateDto.width, boardCreateDto.height)
            cellPositions.shuffle()

            val cells = Cells.of(cellPositions, boardCreateDto.mineCount)
            cells.sortByPosition()

            return cellsToBoard(cells, boardCreateDto.width, boardCreateDto.height)
        }

        private fun cellsToBoard(cells: Cells, width: Int, height: Int) =
            List(height) {
                val startIndex = it * width
                val endIndex = startIndex + width
                cells.take(startIndex, endIndex)
            }.let(::MineBoard)
    }
}
