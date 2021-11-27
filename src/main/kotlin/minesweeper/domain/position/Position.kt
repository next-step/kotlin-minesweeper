package minesweeper.domain.position

data class Position(val x: Int, val y: Int) {

    private lateinit var _adjacentPositions: Positions

    val adjacentPositions: Positions
        get() = Positions.of(_adjacentPositions)

    fun updateAdjacentPositions(allPositions: Positions) {
        val value = CompassDirections.values()
            .map {
                of(x + it.x, y + it.y)
            }.filter {
                checkValidPosition(it.x, it.y) && it in allPositions
            }

        _adjacentPositions = Positions.of(value)
    }

    companion object {
        fun of(x: Int, y: Int): Position {
            require(checkValidPosition(x, y)) { X_AND_Y_GRATER_THEN_MINUS_ONE }
            return Position(x, y)
        }

        private fun checkValidPosition(x: Int, y: Int) = x > -1 && y > -1
        private const val X_AND_Y_GRATER_THEN_MINUS_ONE = "x와 y는 -1 보다 커야 합니다."
    }
}
