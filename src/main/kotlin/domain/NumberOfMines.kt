package domain

@JvmInline
value class NumberOfMines(val value: Int) {
    init {
        require(value >= MIN_VALUE) { "지뢰 개수는 0개 이상이어야 합니다." }
    }

    companion object {
        private const val MIN_VALUE = 0
    }
}
