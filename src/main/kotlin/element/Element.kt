package element

import element.status.CellStatus

interface Element {
    val value: String?
    val status: CellStatus

    fun updateValue(
        newValue: String,
        newStatus: CellStatus,
    ): Element = this
}
