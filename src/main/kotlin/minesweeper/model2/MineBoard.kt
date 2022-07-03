package minesweeper.model2

import minesweeper.dto.MineBoardCreateDto
import minesweeper.model.CellPosition
import minesweeper.model.CellPositions

class MineBoard(
    board: List<Cells>
) : List<Cells> by board {

    private val _board: MutableList<Cells> = board.toMutableList()
    val board: List<Cells>
        get() = _board.toList()

    val mineCount
        get() = board.sumOf { it.mineCount }

    fun openAtPositionAndSurroundingNonMineCells(position: CellPosition) {
        val targetCells = board[position.x.position]
        if (targetCells.isOpenedAt(position.y)) {
            return
        }

        _board[position.x.position][position.y.position] = targetCells.openAt(position.y)

        val surroundingPositions = position.findSurroundingCellPositions()
        if (sumOfMineCountIn(surroundingPositions) > 0) {
            return
        }

        val surroundingClosedNonMineCells = findClosedCellsIn(surroundingPositions)
        surroundingClosedNonMineCells.forEach { openAtPositionAndSurroundingNonMineCells(it.position) }
    }

    private fun sumOfMineCountIn(positions: Set<CellPosition>): Int =
        board.sumOf { it.mineCountIn(positions) }

    private fun findClosedCellsIn(positions: Set<CellPosition>) =
        board.flatMap { it.findClosedCellsIn(positions) }

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
