package mine.sweeper.application.value

@JvmInline
value class Height(val value: Int) {
    init {
        require(value > 0)
    }
}
