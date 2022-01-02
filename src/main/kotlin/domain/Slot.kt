package domain

abstract class Slot(protected var _isChecked: Boolean = false, protected val slotInfo: SlotInfo) {
    abstract fun isMine(): Boolean

    abstract fun setNumberOfNearMines(mineField: List<Slot>)

    fun numberOfNearMines() = slotInfo.numberOfNearMines

    fun changeChecked() {
        _isChecked = true
    }

    fun isChecked() = _isChecked

    fun point() = slotInfo.point
}
