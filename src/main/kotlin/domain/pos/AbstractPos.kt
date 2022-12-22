package domain.pos

data class AbstractPos private constructor(
    override val value: Int
) : Pos {
    init {
        require(value >= MIN_VALUE) { "입력값은 0 이상이어야 합니다." }
    }

    companion object {
        private const val MIN_VALUE = 0
        private const val MAX_CACHE_VALUE = 10

        private val cache: Map<Int, AbstractPos> = (MIN_VALUE..MAX_CACHE_VALUE).associateWith { AbstractPos(it) }

        fun of(value: Int): AbstractPos {
            return cache[value] ?: AbstractPos(value)
        }
    }
}
