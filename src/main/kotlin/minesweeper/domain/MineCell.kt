package minesweeper.domain


data class MineCell(
    override val position: Position,
    override val state: CellState = CellState.CLOSED,
) : Cell(position, state) {
    override fun openResult(): CellOpenResult = CellOpenResult.MINE_FOUND

    override fun copyWithOpen(): MineCell = this.copy(state = CellState.OPENED)

    companion object {
        const val MINE_SIGN = "*"
    }
}
