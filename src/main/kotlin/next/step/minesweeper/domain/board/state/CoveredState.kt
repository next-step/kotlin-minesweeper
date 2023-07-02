package next.step.minesweeper.domain.board.state

object CoveredState : BoardPointState() {

    override fun notifyMine(): CoveredState = this
}
