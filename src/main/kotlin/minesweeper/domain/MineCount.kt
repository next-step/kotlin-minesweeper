package minesweeper.domain

@JvmInline
value class MineCount(private val count: String) {
    init {
        require(count.toIntOrNull() != null) {
            "지뢰 갯수는 숫자이어야함"
        }

        require(count.toInt () > MINIMUM_COUNT) {
            "지뢰 갯수는 0보다 커야함"
        }
    }

    fun getMineCount(): Int {
        return count.toInt()
    }

    companion object {
        const val MINIMUM_COUNT = 0
    }
}
