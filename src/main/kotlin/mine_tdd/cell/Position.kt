package mine_tdd.cell

data class Position(
    val x: Int,
    val y: Int,
) {
    init {
        require(x >= MIN && y >= MIN)
    }

    companion object {
        private const val DELIMITER = ","
        private const val MIN = 0

        fun Position.findNearPosition(): List<Position> {
            val x = listOf(this.x - 1, this.x, this.x + 1).filter { it >= MIN }
            val y = listOf(this.y - 1, this.y, this.y + 1).filter { it >= MIN }
            return x.map { positionX ->
                y.map { Position(positionX, it) }
            }.flatten().filterNot { it == this }
        }
        fun String?.ofPosition(): Position {
            require(this != null)
            val ints = this.split(DELIMITER).mapNotNull { it.toIntOrNull() }
            require(ints.size == 2 && ints.isNotEmpty())
            return Position(ints[0], ints[1])
        }
    }
}
