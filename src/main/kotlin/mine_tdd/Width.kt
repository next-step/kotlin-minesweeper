package mine_tdd

@JvmInline
value class Width(private val width: Int) {
    init {
        require(width > 0)
    }

    fun value(): Int = width

    companion object {
        private const val MIN_VALUE = 0
        fun value(width: Int?): Width = width?.coerceAtLeast(MIN_VALUE)?.let(::Width) ?: Width(MIN_VALUE)
    }
}
