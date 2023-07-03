package next.step.minesweeper.domain.board.state

object MineFreeState : BoardPointState() {

    override fun notifyMine(): NearMineState = NearMineState.one()

    override fun uncover(): BoardPointState = this

    override fun hasNoMine(): Boolean = true
}
