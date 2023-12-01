package minesweeper.domain

data class Position(
    val y: Int,
    val x: Int
) {
    init {
        require(y > 0) { "입력값: $y, y는 0이거나 음수일 수 없습니다" }
        require(x > 0) { "입력값: $x, x는 0이거나 음수일 수 없습니다" }
    }

    fun aroundPositions(): List<Position> {
        return listOfNotNull(
            topOrNull(),
            bottomOrNull(),
            leftOrNull(),
            rightOrNull(),
            topOrNull()?.leftOrNull(),
            topOrNull()?.rightOrNull(),
            bottomOrNull()?.leftOrNull(),
            bottomOrNull()?.rightOrNull()
        )
    }

    private fun leftOrNull(): Position? = runCatching { Position(y, x - 1) }.getOrNull()
    private fun rightOrNull(): Position? = runCatching { Position(y, x + 1) }.getOrNull()
    private fun topOrNull(): Position? = runCatching { Position(y - 1, x) }.getOrNull()
    private fun bottomOrNull(): Position? = runCatching { Position(y + 1, x) }.getOrNull()
}
