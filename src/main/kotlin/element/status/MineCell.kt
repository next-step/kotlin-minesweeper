package element.status

import element.showable.Hide
import element.showable.Showable

data class MineCell(
    override val visibility: Showable = Hide,
) : CellStatus {
    override fun isMine(): Boolean = true
}
