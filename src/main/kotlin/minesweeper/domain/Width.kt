package minesweeper.domain

class Width(val value: Int) {
    init {
        require(value > MINIMUM_WIDTH)
    }

    companion object {
        private const val MINIMUM_WIDTH = 0
    }
}
