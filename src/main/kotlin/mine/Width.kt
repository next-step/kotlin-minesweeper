package mine

@JvmInline
value class Width(val value: Int) {
    init {
        require(value >= MIN_VALUE)
    }

    companion object {
        const val MIN_VALUE = 0

        fun value(value: Int?): Width = value?.coerceAtLeast(MIN_VALUE)?.let(::Width) ?: Width(MIN_VALUE)
    }
}
