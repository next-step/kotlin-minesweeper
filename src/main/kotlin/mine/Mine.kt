package mine

@JvmInline
value class Mine(val value: Int = MIN_VALUE) {
    init {
        require(value >= MIN_VALUE)
    }

    companion object {
        const val MIN_VALUE = 0

        fun value(value: Int?): Mine = value?.coerceAtLeast(MIN_VALUE)?.let(::Mine) ?: Mine(MIN_VALUE)
    }
}
