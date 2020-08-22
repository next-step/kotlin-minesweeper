package minesweeper.domain

data class MineLessSpot(
    override val streetNumber: Int,
    override val nthPosition: Int
) : Spot {
    private var nearMineCount: Int = 0

    override fun getMineCount(): Int = nearMineCount

    override fun addMineCount(spot: Spot) {
        if (spot is MineSpot) nearMineCount++
    }

    override fun toString(): String = nearMineCount.toString()
}
