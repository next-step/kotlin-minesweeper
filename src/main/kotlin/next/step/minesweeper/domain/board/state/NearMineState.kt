package next.step.minesweeper.domain.board.state

import next.step.minesweeper.domain.mine.MineCount

data class NearMineState(private val nearMineCount: MineCount) : BoardPointState() {

    override fun notifyMine(): NearMineState = NearMineState(nearMineCount.increase(1))

    override fun cover(): CoveredState = CoveredState(this)

    override fun uncover(): NearMineState = this

    override fun hasNoMine(): Boolean = true

    fun count(): Int = nearMineCount.count()

    companion object {
        fun one(): NearMineState = NearMineState(MineCount(1))
    }
}
