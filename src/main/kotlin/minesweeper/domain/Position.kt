package minesweeper.domain

private const val MINIMUM_POSITION_NUMBER = 1

data class Position(
    val x: Int,
    val y: Int,
) {
    init {
        require(x >= MINIMUM_POSITION_NUMBER && y >= MINIMUM_POSITION_NUMBER) { "x, y 위치 조건은 1이상의 값만 허용합니다." }
    }

    fun getNearPositions(): List<Position> {
        return MineSearchDirection.values()
            .filter { isSatisfiedNextPositionCondition(it) }
            .map { this + it }
    }

    private fun isSatisfiedNextPositionCondition(mineSearchDirection: MineSearchDirection): Boolean {
        return (x + mineSearchDirection.x >= MINIMUM_POSITION_NUMBER) && (y + mineSearchDirection.y >= MINIMUM_POSITION_NUMBER)
    }

    operator fun plus(mineSearchDirection: MineSearchDirection) = Position(
        x = x + mineSearchDirection.x,
        y = y + mineSearchDirection.y,
    )
}
