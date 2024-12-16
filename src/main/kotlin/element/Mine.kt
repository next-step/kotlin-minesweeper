package element

import element.showable.Hide
import element.showable.Showable
import element.status.CellStatus
import element.status.MineCell

data class Mine(
    override val value: String = DEFAULT,
    override val status: CellStatus = MineCell,
    override val visibility: Showable = Hide,
) : Element {
    override fun open(): Element? = null

    override fun isOpen(): Boolean = false

    companion object {
        const val DEFAULT = "*"

        fun ready() = Mine(DEFAULT)
    }
}
