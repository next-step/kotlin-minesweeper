package minesweeper.domain

data class Mine(
    val position: MinePosition,
    val symbol: String = MINE_SYMBOL
) {
    val x: Int = this.position.x
    val y: Int = this.position.y

    constructor(dimensionBounds: MapDimension, positionStrategy: PositionStrategy = RandomPositionStrategy) : this(
        MinePosition(
            positionStrategy.setXPosition(dimensionBounds.width),
            positionStrategy.setYPosition(dimensionBounds.height)
        )
    )

    fun isSameRow(nthRow: Int): Boolean = nthRow == this.y

    companion object {
        const val MINE_SYMBOL = "*"
    }
}
