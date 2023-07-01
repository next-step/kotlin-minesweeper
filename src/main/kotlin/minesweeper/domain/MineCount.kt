package minesweeper.domain

@JvmInline
value class MineCount(private val count: Int) {

    fun getMineCount(): Int {
        return count
    }

    companion object {
        private const val MINIMUM_COUNT = 0
        fun of(value: String): MineCount {
            require(value.toIntOrNull() != null) {
                "지뢰 갯수는 숫자이어야함"
            }
            val count = value.toInt()
            require(count > MINIMUM_COUNT) {
                "지뢰 갯수는 0보다 커야함"
            }
            return MineCount(count)
        }
    }
}
