package element

import element.showable.Hide
import element.showable.Show
import element.showable.Showable
import element.status.CellStatus
import element.status.EmptyCell
import element.status.NumberCell

data class Cell(
    override val value: String? = null,
    override val status: CellStatus = EmptyCell,
    override val visibility: Showable = Hide,
) : Element {
    override fun open(): Element =
        when {
            isNotNumberCell() -> this
            visibility is Show -> this
            else -> this.copy(visibility = Show)
        }

    override fun isOpen(): Boolean =
        when {
            isNotNumberCell() -> false
            value?.toIntOrNull() != 0 -> false
            visibility !is Hide -> false
            else -> true
        }

    private fun isNotNumberCell() = status !is NumberCell

    override fun updateValue(
        newValue: String,
        newStatus: CellStatus,
    ): Element = Cell(value = newValue, status = newStatus)

    companion object {
        fun ready() = Cell()
    }
}
