package element

import element.status.CellStatus

interface Element {
    val value: String?
    val status: CellStatus

    fun open(): Element?

    fun isOpenable(): Boolean

    fun isMine(): Boolean = false

    fun updateValue(
        newValue: String,
        newStatus: CellStatus,
    ): Element = this
}
