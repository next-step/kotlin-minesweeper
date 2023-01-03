package minesweeper.domain

data class CleanCell(
    override val position: Position,
    val nearMineCount: Int,
    override val state: CellState = CellState.CLOSED,
) : Cell(position, state) {
    constructor(position: Position, minePositions: List<Position>) : this(
        position,
        position.getNearPositions().count { minePositions.contains(it) },
        CellState.CLOSED
    )

    override fun openResult(): CellOpenResult =
        if (noNearMine()) CellOpenResult.SPREAD_NEEDED else CellOpenResult.MINE_NOT_FOUND

    override fun copyWithOpen(): CleanCell = this.copy(state = CellState.OPENED)

    private fun noNearMine(): Boolean = nearMineCount == 0
}

