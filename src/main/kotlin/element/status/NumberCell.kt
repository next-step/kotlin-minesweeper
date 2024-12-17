package element.status

import element.showable.Hide
import element.showable.Show
import element.showable.Showable

data class NumberCell(
    override val visibility: Showable = Hide,
) : CellStatus {
    override fun open(): CellStatus? = this.copy(visibility = Show).takeIf { isOpenable() }

    override fun isOpenable(): Boolean = visibility == Hide
}
