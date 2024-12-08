package cell

interface Element {
    val value: String

    fun updateValue(newValue: String): Element = this
}
