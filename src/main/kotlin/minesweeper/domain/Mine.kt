package minesweeper.domain

data class Mine(private val position: Pair<Int, Int>, val symbol: String = MINE_SYMBOL) {

    fun getX(): Int = this.position.first
    fun getY(): Int = this.position.second

    fun isIn(nthRow: Int): Boolean = nthRow == this.getY()

    companion object {
        private const val MINE_SYMBOL = "*"

        fun createMine(mapArea: Pair<Int, Int>, positionStrategy: PositionStrategy): Mine {
            val x = positionStrategy.setXPosition(Width.from(mapArea.second))
            val y = positionStrategy.setYPosition(Height.from(mapArea.first))
            return Mine(Pair(x, y))
        }
    }
}
