package minesweeper.model

data class Cell(
    private val type: CellType,
    val position: CellPosition,
) {

    fun findSurroundingMineCountSum(board: MineBoard): Int {
        val surroundingPositions = position.findSurroundingCellPositions()
        return board.sumOfMineCountIn(surroundingPositions)
    }

    fun isMineIn(positions: Set<CellPosition>): Boolean = isMine() && positions.contains(position)

    fun isMine(): Boolean = type.isMine()

    companion object {
        fun mine(position: CellPosition): Cell = Cell(CellType.MINE, position)

        fun nonMine(position: CellPosition): Cell = Cell(CellType.NON_MINE, position)
    }
}
