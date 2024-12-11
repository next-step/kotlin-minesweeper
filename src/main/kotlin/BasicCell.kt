data class BasicCell(private val value: Int = 0) : Cell {
    override fun display(): String = CLOSED_SYMBOL

    companion object {
        private const val CLOSED_SYMBOL = "■"
    }
}
