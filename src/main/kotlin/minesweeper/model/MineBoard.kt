package minesweeper.model

import minesweeper.dto.MineBoardCreateDto

class MineBoard(
    board: List<Cells>
) : List<Cells> by board {

    private val _board: MutableList<Cells> = board.toMutableList()
    val board: List<Cells>
        get() = _board.toList()

    val isOngoing: Boolean
        get() = gameStatus.isOngoing

    val gameStatus
        get(): GameStatus {
            if (containsOpenedMine) {
                return GameStatus.LOST
            }

            if (mineCount == closedCellCount) {
                return GameStatus.WIN
            }

            return GameStatus.ONGOING
        }

    private val mineCount
        get(): Int = board.sumOf { it.mineCount }

    private val containsOpenedMine
        get(): Boolean = board.any { it.containsOpenedMine() }

    private val closedCellCount
        get(): Int = board.sumOf { it.closedCellCount }

    fun openAtPositionAndSurroundingNonMineCells(position: CellPosition) {
        val targetCells = board[position.y.position]
        if (targetCells.isOpenedAt(position.x)) {
            return
        }

        _board[position.y.position][position.x.position] = targetCells.openAt(position.x)

        val surroundingPositions = position.findSurroundingCellPositions()
        if (sumOfMineCountIn(surroundingPositions) > 0) {
            return
        }

        val surroundingClosedNonMineCells = findClosedCellsIn(surroundingPositions)
        surroundingClosedNonMineCells.forEach { openAtPositionAndSurroundingNonMineCells(it.position) }
    }

    fun sumOfMineCountIn(positions: Set<CellPosition>): Int =
        board.sumOf { it.mineCountIn(positions) }

    private fun findClosedCellsIn(positions: Set<CellPosition>): List<Cell> =
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
