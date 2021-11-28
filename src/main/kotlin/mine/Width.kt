package mine

@JvmInline
value class Width(private val width: Int) {
    init {
        require(width >= MIN_VALUE)
    }

    companion object {
        const val MIN_VALUE = 0

        fun value(value: Int?): Width = value?.coerceAtLeast(MIN_VALUE)?.let(::Width) ?: Width(MIN_VALUE)
    }
}
