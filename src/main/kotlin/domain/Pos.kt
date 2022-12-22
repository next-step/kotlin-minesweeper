package domain

data class Pos private constructor(
    val value: Int
) {
    init {
        require(value >= MIN_VALUE) { "입력값은 0 이상이어야 합니다." }
    }

    fun isNotPossiblePlus(target: Pos): Boolean = value + target.value < 0

    operator fun plus(target: Pos): Pos = of(value + target.value)

    companion object {
        private const val MIN_VALUE = -1
        private const val MAX_CACHE_VALUE = 10

        private val cache: Map<Int, Pos> = (MIN_VALUE..MAX_CACHE_VALUE).associateWith { Pos(it) }

        fun of(value: Int): Pos {
            return cache[value] ?: Pos(value)
        }
    }
}
