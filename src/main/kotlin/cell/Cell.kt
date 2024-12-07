package cell

data class Cell(
    override val value: Char = DEFAULT,
) : Element {
    override fun updateValue(newValue: Char): Element = Cell(value = newValue)

    companion object {
        private const val DEFAULT = 'C'

        fun ready() = Cell(DEFAULT)
    }
}
