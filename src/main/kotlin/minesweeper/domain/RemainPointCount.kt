package minesweeper.domain

data class RemainPointCount(
    private var count: Int,
    val allowNegative: Boolean = false
) {

    fun current(): Int = count

    fun decreaseAndGet(input: Int = 1): Int {
        check(isReductionPossible(input)) {
            "더 이상 값을 감소시킬 수 없습니다: Current: $count"
        }
        count -= input
        return count
    }

    private fun isReductionPossible(input: Int): Boolean = count - input >= 0 || allowNegative

    fun isZero(): Boolean = count == 0
}
