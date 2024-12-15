package minesweeper.domain

data class Coordinate(
    val y: Int,
    val x: Int,
) {
    val neighbors: List<Coordinate> by lazy { neighbors() }

    init {
        require(y in 0..MAX_VALUE) { "y축 좌표는 0 이상이어야 합니다." }
        require(x in 0..MAX_VALUE) { "x축 좌표는 0 이상이어야 합니다." }
    }

    private fun neighbors(): List<Coordinate> =
        Direction
            .entries
            .mapNotNull {
                val nextY = y + it.dy
                val nextX = x + it.dx
                if (nextY in 0..MAX_VALUE && nextX in 0..MAX_VALUE) {
                    Coordinate(y = nextY, x = nextX)
                } else {
                    null
                }
            }

    companion object {
        // https://www.perplexity.ai/search/largest-ever-minesweeper-game-EXup1APhRFK6xGo6j8Ps1g#0
        const val MAX_VALUE = 1_000_000
    }
}
