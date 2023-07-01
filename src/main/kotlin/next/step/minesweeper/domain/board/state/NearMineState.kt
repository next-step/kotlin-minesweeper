package next.step.minesweeper.domain.board.state

import next.step.minesweeper.domain.mine.MineCount

data class NearMineState(private var nearMineCount: MineCount) : BoardPointState() {

    fun count(): Int = nearMineCount.count

    fun increase() = nearMineCount++

    companion object {
        fun one(): NearMineState = NearMineState(MineCount(1))
    }
}
