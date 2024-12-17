package element

import element.status.CellStatus
import element.status.EmptyCell

data class Cell(
    override val value: String? = null,
    override val status: CellStatus = EmptyCell(),
) : Element {
    override fun open(): Element =
        status
            .open()
            ?.let { this.copy(status = it) }
            ?: this

    override fun isOpenable(): Boolean = status.isOpenable() && value?.toIntOrNull() == 0

    override fun updateValue(
        newValue: String,
        newStatus: CellStatus,
    ): Element = Cell(value = newValue, status = newStatus)

    companion object {
        fun ready() = Cell()
    }
}
