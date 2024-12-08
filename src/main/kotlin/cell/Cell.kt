package cell

data class Cell(
    override val value: String = DEFAULT,
) : Element {
    override fun updateValue(newValue: String): Element = Cell(value = newValue)

    companion object {
        private const val DEFAULT = "C"

        fun ready() = Cell(DEFAULT)
    }
}
