package minesweeper.domain.mine

data class Mine(
    val mineCount: Int,
) {
    init {
        require(mineCount > 0) { INVALID_VALUE }
    }

    companion object {
        private const val INVALID_VALUE = "잘못된 값을 입력하셨습니다."
    }
}
