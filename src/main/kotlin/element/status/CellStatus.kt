package element.status

import element.showable.Showable

interface CellStatus {
    val visibility: Showable

    fun isMine(): Boolean = false

    fun open(): CellStatus? = null

    fun isOpenable(): Boolean = false
}
