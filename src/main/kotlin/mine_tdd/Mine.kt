package mine_tdd

@JvmInline
value class Mine(private val mine: Int = DEFAULT_COUNT) {
    init {
        require(mine >= DEFAULT_COUNT)
    }

    fun value(): Int = mine

    companion object {
        private const val DEFAULT_COUNT = 0
        fun value(mine: Int?): Mine = mine?.coerceAtLeast(DEFAULT_COUNT)?.let(::Mine) ?: Mine(DEFAULT_COUNT)
    }
}
