package domain

abstract class Slot(val isChecked: Boolean = false, protected val slotInfo: SlotInfo) {
    abstract fun isMine(): Boolean

    abstract fun setNumberOfNearMines(mineField: List<Slot>)

    fun numberOfNearMines() = slotInfo.numberOfNearMines

    fun point() = slotInfo.point
}
