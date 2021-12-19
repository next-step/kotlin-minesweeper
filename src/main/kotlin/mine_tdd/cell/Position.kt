package mine_tdd.cell

data class Position(
    val x: Int,
    val y: Int,
) {
    init {
        require(x >= MIN && y >= MIN)
    }

    companion object {
        private const val MIN = 0

        fun Position.findNearPosition(): List<Position> {
            val x = listOf(this.x - 1, this.x, this.x + 1)
            val y = listOf(this.y - 1, this.y, this.y + 1)
            return x.map { positionX ->
                y.map { Position(positionX, it) }
            }.flatten().filterNot { it == this }
        }
    }
}
