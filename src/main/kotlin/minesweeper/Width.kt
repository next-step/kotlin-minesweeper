package minesweeper

data class Width(val value: Int) {
    init {
        require(value > ZERO) { "너비는 1이상이어야 합니다. input = $value" }
    }

    companion object {
        private const val ZERO = 0
    }
}
