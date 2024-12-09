package cell

import cell.status.CellStatus

interface Element {
    val value: String?
    val status: CellStatus

    fun updateValue(newValue: String): Element = this
}
