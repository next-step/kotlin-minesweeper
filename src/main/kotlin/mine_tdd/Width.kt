package mine_tdd

@JvmInline
value class Width(private val width: Int) {
    init {
        require(width > 0)
    }

    fun value(): Int = width
}
