package mine_tdd.cell

class Position(
    val x: Int,
    val y: Int,
) {
    init {
        require(x >= MIN && y >= MIN)
    }

    companion object {
        private const val MIN = 0
    }
}
