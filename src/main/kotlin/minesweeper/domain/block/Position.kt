package minesweeper.domain.block

data class Position(private val x: Int = DEFAULT_X, private val y: Int = DEFAULT_Y) {

    companion object {
        private const val DEFAULT_X = 0
        private const val DEFAULT_Y = 0
    }
}
