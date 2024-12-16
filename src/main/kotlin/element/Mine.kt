package element

import element.status.CellStatus
import element.status.MineCell

data class Mine(
    override val value: String = DEFAULT,
    override val status: CellStatus = MineCell,
) : Element {
    companion object {
        const val DEFAULT = "*"

        fun ready() = Mine(DEFAULT)
    }
}
