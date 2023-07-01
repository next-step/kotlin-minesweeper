package mine.sweeper.domain.value

@JvmInline
value class Width(val value: Int) {
    init {
        require(value > 0)
    }
}
