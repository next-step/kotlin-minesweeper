package v2minesweeper.domain

@JvmInline
value class MineNumber(
    val value: Int,
) {
    init {
        require(value >= MIN_MINE_NUMBER_VALUE) { "지뢰 갯수는 0보다 큰 정수만 가능합니다. value = $value" }
    }

    companion object {
        private const val MIN_MINE_NUMBER_VALUE = 0
    }
}
