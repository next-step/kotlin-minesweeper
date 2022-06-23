package minesweeper.model

data class Cell(
    private val type: CellType,
    val position: CellPosition,
) {

    fun findSurroundingMineCountSum(board: MineBoard): Int {
        val surroundingPositions = position.findSurroundingCellPositions()
        return board.board.sumOf {
            it.count { cells -> cells.isMineIn(surroundingPositions) }
        }
    }

    fun isMine() = type.isMine()

    private fun isMineIn(positions: Set<CellPosition>) = isMine() && positions.contains(position)

    companion object {
        fun mine(position: CellPosition) = Cell(CellType.MINE, position)

        fun nonMine(position: CellPosition) = Cell(CellType.NON_MINE, position)
    }
}
