package domain.block

data class SurroundingMineCount(val value: Int) {

    init {
        require(value in MIN_COUNT..MAX_COUNT) { "주변 지뢰의 개수는 $MIN_COUNT~$MAX_COUNT 사이의 값이어야 합니다. value: $value" }
    }

    fun isZero() = value == 0

    companion object {
        private const val MIN_COUNT = 0
        private const val MAX_COUNT = 8
    }
}
