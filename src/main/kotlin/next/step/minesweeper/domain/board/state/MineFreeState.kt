package next.step.minesweeper.domain.board.state

object MineFreeState : BoardPointState() {

    override fun notifyMine(): NearMineState = NearMineState.one()

    override fun cover(): CoveredState = CoveredState(this)

    override fun uncover(): BoardPointState = this
}
