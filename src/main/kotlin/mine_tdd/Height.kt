package mine_tdd

@JvmInline
value class Height(private val height: Int) {
    init {
        require(height > MIN_VALUE)
    }

    fun value(): Int = height

    companion object {
        private const val MIN_VALUE = 0
        fun value(height: Int?): Height = height?.coerceAtLeast(MIN_VALUE)?.let(::Height) ?: Height(MIN_VALUE)
    }
}
