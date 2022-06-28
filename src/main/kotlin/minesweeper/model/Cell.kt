package minesweeper.model

data class Cell(
    private val type: CellType,
    val position: CellPosition,
    var isOpened: Boolean = false,
) {

    fun findSurroundingMineCountSum(board: MineBoard): Int {
        val surroundingPositions = position.findSurroundingCellPositions()
        return board.sumOfMineCountIn(surroundingPositions)
    }

    fun openMeAndSurroundingNonMineCells(board: MineBoard) {
        isOpened = true

        val surroundingPositions = position.findSurroundingCellPositions()
        if (board.sumOfMineCountIn(surroundingPositions) > 0) {
            return
        }

        val surroundingUnopenedNonMineCells = board.findUnopenedCellsIn(surroundingPositions)
        surroundingUnopenedNonMineCells.forEach { it.openMeAndSurroundingNonMineCells(board) }
    }

    fun isMineIn(positions: Set<CellPosition>): Boolean = isMine() && positions.contains(position)

    fun isMine(): Boolean = type.isMine()

    fun isUnopenedAndIn(positions: Set<CellPosition>): Boolean = !isOpened && positions.contains(position)

    companion object {
        fun mine(position: CellPosition): Cell = Cell(CellType.MINE, position)

        fun nonMine(position: CellPosition): Cell = Cell(CellType.NON_MINE, position)
    }
}
