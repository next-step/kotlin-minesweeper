package next.step.minesweeper.domain.board.state

import next.step.minesweeper.domain.mine.MineCount

data class NearMineState(private val nearMineCount: MineCount) : BoardPointState() {

    override fun notifyMine(): NearMineState = NearMineState(nearMineCount.increase(1))

    fun count(): Int = nearMineCount.count()

    companion object {
        fun one(): NearMineState = NearMineState(MineCount(1))
    }
}
