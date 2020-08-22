package minesweeper.domain

data class MineSpot(
    override val streetNumber: Int,
    override val nthPosition: Int,
    private val baseSpot: MineLessSpot = MineLessSpot(0, 0)
) : Spot by baseSpot {

    override fun toString(): String = SpotSymbol.MINE.symbol
}
