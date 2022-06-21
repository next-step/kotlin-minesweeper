package minesweeper.domain

@JvmInline
value class MineCount(private val count: Int) {
    init {
        require(count > 0) { "지뢰는 0개 보다 많아야합니다." }
    }

    fun toInt() = count
    operator fun compareTo(value: Int): Int {
        return count.compareTo(value)
    }
}
