package minesweeper.model

abstract class Cell(
    val position: CellPosition,
) {
    abstract val isOpened: Boolean

    abstract val isMine: Boolean

    val isMineAndOpened: Boolean
        get() = isMine && isOpened

    fun isMineAndIn(positions: Set<CellPosition>) = isMine && isIn(positions)

    fun isClosedAndIn(positions: Set<CellPosition>) = !isOpened && isIn(positions)

    fun findSurroundingMineCountSum(board: MineBoard): Int {
        val surroundingPositions = position.findSurroundingCellPositions()
        return board.sumOfMineCountIn(surroundingPositions)
    }

    private fun isIn(positions: Collection<CellPosition>) = positions.contains(position)

    abstract fun open(): Cell
}

class ClosedMine(
    position: CellPosition,
    override val isOpened: Boolean = false,
    override val isMine: Boolean = true,
) : Cell(position) {

    override fun open(): Cell = OpenedMine(position)
}

class ClosedNonMine(
    position: CellPosition,
    override val isOpened: Boolean = false,
    override val isMine: Boolean = false,
) : Cell(position) {

    override fun open(): Cell = OpenedNonMine(position)
}

class OpenedMine(
    position: CellPosition,
    override val isOpened: Boolean = true,
    override val isMine: Boolean = true,
) : Cell(position) {

    override fun open(): Cell = this
}

class OpenedNonMine(
    position: CellPosition,
    override val isOpened: Boolean = true,
    override val isMine: Boolean = false,
) : Cell(position) {

    override fun open(): Cell = this
}
