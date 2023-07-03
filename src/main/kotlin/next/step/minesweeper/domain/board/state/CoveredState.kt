package next.step.minesweeper.domain.board.state

data class CoveredState(private val uncoveredState: BoardPointState) : BoardPointState() {

    override fun notifyMine(): CoveredState = CoveredState(uncoveredState.notifyMine())

    override fun uncover(): BoardPointState = uncoveredState

    override fun hasNoMine(): Boolean = uncoveredState != MineState
}
