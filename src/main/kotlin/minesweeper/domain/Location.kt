package minesweeper.domain

data class Location(val x: Int, val y: Int) {
    init {
        require(x >= 0) { ERROR_INVALID_POS }
        require(y >= 0) { ERROR_INVALID_POS }
    }

    companion object {
        private const val ERROR_INVALID_POS = "좌표가 0보다 작습니다"
    }
}
