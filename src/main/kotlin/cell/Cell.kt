package cell

data class Cell(
    override val value: String? = null
) : Element {
    override fun updateValue(newValue: String): Element = Cell(value = newValue)

    companion object {
        fun ready() = Cell()
    }
}
