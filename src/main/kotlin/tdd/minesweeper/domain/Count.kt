package tdd.minesweeper.domain

data class Count(private var value: Int) {

    val current: Int
        get() = value

    fun decreaseAndGet(input: Int = 1): Int {
        require(isReductionPossible(input)) {
            "더 이상 감소시킬 수 없습니다. [current: $current, input: $input]"
        }
        value -= input
        return value
    }

    private fun isReductionPossible(input: Int): Boolean = value >= input
}
