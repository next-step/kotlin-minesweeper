package cell

import cell.status.CellStatus
import cell.status.EmptyCell

data class Cell(
    override val value: String? = null,
    override val status: CellStatus = EmptyCell,
) : Element {
    override fun updateValue(newValue: String): Element = Cell(value = newValue, status = status)

    companion object {
        fun ready() = Cell()
    }
}
