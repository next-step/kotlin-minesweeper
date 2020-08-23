package minesweeper.domain

data class MineLessSpot(
    override val streetNumber: Int,
    override val nthPosition: Int
) : Spot {
    private var nearByMineCount: Int = 0

    override fun getMineCount(): Int = nearByMineCount

    override fun addCountIfIsMineSpot(spot: Spot) {
        if (spot is MineSpot) nearByMineCount++
    }

    override fun toString(): String = nearByMineCount.toString()
}
