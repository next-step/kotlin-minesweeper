package minesweeper.domain

class Height(value: Int) {
    init {
        require(value > MINIMUM_HEIGHT)
    }

    companion object {
        private const val MINIMUM_HEIGHT = 0
    }
}
