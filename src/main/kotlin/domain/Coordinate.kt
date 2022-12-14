package domain

data class Coordinate private constructor(
    val value: Int
) {

    init {
        require(value >= MIN_VALUE) { "위치는 0 이상이어야 합니다." }
    }

    companion object {
        private const val MIN_VALUE = 0
        private const val MAX_CACHE_VALUE = 10

        private val cache = (MIN_VALUE..MAX_CACHE_VALUE).associateWith { Coordinate(it) }

        fun of(value: Int): Coordinate {
            return cache[value] ?: Coordinate(value)
        }
    }
}
