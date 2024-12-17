package element.status

import element.showable.Hide
import element.showable.Showable

data class EmptyCell(
    override val visibility: Showable = Hide,
) : CellStatus
