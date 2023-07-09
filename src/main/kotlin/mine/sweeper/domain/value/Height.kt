package mine.sweeper.domain.value

@JvmInline
value class Height(val value: Int) {
    init {
        require(value > 0)
    }
}
