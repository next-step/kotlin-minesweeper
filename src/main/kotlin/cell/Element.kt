package cell

interface Element {
    val value: Char

    fun updateValue(newValue: Char): Element = this
}
