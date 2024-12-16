package element

import element.showable.Showable
import element.status.CellStatus

interface Element {
    val value: String?
    val status: CellStatus
    val visibility: Showable

    fun open(): Element?

    fun isOpen(): Boolean

    fun updateValue(
        newValue: String,
        newStatus: CellStatus,
    ): Element = this
}
