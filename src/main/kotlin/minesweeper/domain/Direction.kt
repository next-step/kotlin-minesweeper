package minesweeper.domain

data class Direction(val dx: Int, val dy: Int) {
    companion object {
        val eightWays: List<Direction> = listOf(
            Direction(-1, -1),
            Direction(0, -1),
            Direction(1, -1),
            Direction(-1, 0),
            Direction(1, 0),
            Direction(-1, 1),
            Direction(0, 1),
            Direction(1, 1),
        )
    }
}
