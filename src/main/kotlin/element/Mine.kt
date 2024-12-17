package element

import element.status.CellStatus
import element.status.MineCell

data class Mine(
    override val value: String = DEFAULT,
    override val status: CellStatus = MineCell(),
) : Element {
    override fun open(): Element? = null

    override fun isOpenable(): Boolean = false

    override fun isMine(): Boolean = true

    companion object {
        const val DEFAULT = "*"

        fun ready() = Mine(DEFAULT)
    }
}
