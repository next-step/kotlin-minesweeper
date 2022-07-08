package minesweeper.model

abstract class Cell(
    val position: CellPosition,
) {

    val isMineAndOpened: Boolean
        get() = isMine && isOpened

    abstract val isOpened: Boolean

    abstract val isMine: Boolean

    fun isMineAndIn(positions: Set<CellPosition>): Boolean = isMine && isIn(positions)

    fun isClosedAndIn(positions: Set<CellPosition>): Boolean = !isOpened && isIn(positions)

    fun findSurroundingMineCountSum(board: MineBoard): Int {
        val surroundingPositions = position.findSurroundingCellPositions()
        return board.sumOfMineCountIn(surroundingPositions)
    }

    private fun isIn(positions: Collection<CellPosition>): Boolean = positions.contains(position)

    abstract fun open(): Cell
}

class ClosedMine(
    position: CellPosition,
) : Cell(position) {

    override val isOpened: Boolean = false

    override val isMine: Boolean = true

    override fun open(): Cell = OpenedMine(position)
}

class ClosedNonMine(
    position: CellPosition,
) : Cell(position) {

    override val isOpened: Boolean = false

    override val isMine: Boolean = false

    override fun open(): Cell = OpenedNonMine(position)
}

class OpenedMine(
    position: CellPosition,
) : Cell(position) {

    override val isOpened: Boolean = true

    override val isMine: Boolean = true

    override fun open(): Cell = this
}

class OpenedNonMine(
    position: CellPosition,
) : Cell(position) {

    override val isOpened: Boolean = true

    override val isMine: Boolean = false

    override fun open(): Cell = this
}
