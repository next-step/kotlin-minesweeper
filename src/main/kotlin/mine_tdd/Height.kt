package mine_tdd

@JvmInline
value class Height(private val height: Int) {
    init {
        require(height > 0)
    }

    fun value(): Int = height
}
