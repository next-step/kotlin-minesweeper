package mine

@JvmInline
value class Height(private val height: Int) {
    init {
        require(height >= MIN_VALUE)
    }

    companion object {
        const val MIN_VALUE = 0

        fun value(value: Int?): Height = value?.coerceAtLeast(MIN_VALUE)?.let(::Height) ?: Height(MIN_VALUE)
    }
}
