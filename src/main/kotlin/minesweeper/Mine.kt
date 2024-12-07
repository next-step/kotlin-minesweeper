package minesweeper

data class Mine(val value: Int) {
    init {
        require(value > ZERO) { "지뢰는 1개 이상 이어야 합니다. input = $value" }
    }

    companion object {
        private const val ZERO = 0
    }
}