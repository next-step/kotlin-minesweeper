package model

data class IndexWithMax(
    private val index: Int,
    private val max: Int
) {
    fun index(): Int {
        return index
    }

    init {
        require(index >= MIN) { "index 는 음수일 수 없습니다!" }
        require(index <= max) { "index 는 max 보다 클 수 없습니다!" }
    }

    fun aroundIndexesWithMax(): List<IndexWithMax> {
        return IntRange((index - AROUND_STEP).coerceAtLeast(MIN), (index + AROUND_STEP).coerceAtMost(max))
            .map {
                IndexWithMax(it, max)
            }
    }

    companion object {
        private const val MIN = 0
        private const val AROUND_STEP = 1
    }
}