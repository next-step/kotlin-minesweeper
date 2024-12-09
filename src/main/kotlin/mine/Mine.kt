package mine

import cell.Element
import cell.status.CellStatus
import cell.status.MineCell

data class Mine(
    override val value: String = DEFAULT,
    override val status: CellStatus = MineCell,
) : Element {
    companion object {
        const val DEFAULT = "*"

        fun ready() = Mine(DEFAULT)
    }
}
