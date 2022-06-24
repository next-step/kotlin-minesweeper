package domain

@JvmInline
value class NumberOfMines(val value: Int) {
    init {
        require(value >= MIN_VALUE) {
            "지뢰 개수는 ${MIN_VALUE}개 이상이어야 합니다. 입력된 지뢰 개수 = $value"
        }
    }

    companion object {
        private const val MIN_VALUE = 1
    }
}
