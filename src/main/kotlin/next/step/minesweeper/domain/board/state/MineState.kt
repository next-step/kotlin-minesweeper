package next.step.minesweeper.domain.board.state

object MineState : BoardPointState() {

    override fun notifyMine(): MineState = this

    override fun uncover(): BoardPointState = this

    override fun hasNoMine(): Boolean = false
}
