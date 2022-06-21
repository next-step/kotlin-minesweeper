package minesweeper.model

class MineBoard private constructor(
    val board: List<Cells>
) {

    companion object {
        fun of(boardCreateDto: MineBoardCreateDto): MineBoard {
            val mineCells = List(boardCreateDto.mineCount) {
                Cell.mine()
            }

            val closeCells = List(boardCreateDto.closeCellCount) {
                Cell.close()
            }

            val mergedCells = mineCells.plus(closeCells)
                .shuffled()

            return mergedCellsToBoard(mergedCells, boardCreateDto.width, boardCreateDto.height)
        }

        private fun mergedCellsToBoard(mergedCells: List<Cell>, width: Int, height: Int): MineBoard {
            val board = List(height) {
                val startIndex = it * width
                val endIndex = startIndex + width
                Cells(mergedCells.subList(startIndex, endIndex))
            }

            return MineBoard(board)
        }
    }
}
