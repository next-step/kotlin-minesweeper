package mine.sweeper.application.value

@JvmInline
value class Width(val value: Int) {
    init {
        require(value > 0)
    }
}
