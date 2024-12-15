package minesweeper.domain

data class Coordinate(
    val y: Int,
    val x: Int,
) {
    init {
        require(y in 0..MAX_VALUE) { "y축 좌표는 0 이상이어야 합니다." }
        require(x in 0..MAX_VALUE) { "x축 좌표는 0 이상이어야 합니다." }
    }

    companion object {
        private const val MAX_VALUE = 1_000_000
        // https://www.perplexity.ai/search/largest-ever-minesweeper-game-EXup1APhRFK6xGo6j8Ps1g#0
    }
}
