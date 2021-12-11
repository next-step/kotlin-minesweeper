package domain

abstract class Slot(val isChecked: Boolean = false, val numberOfNearMines: Int) {
    abstract fun isMine(): Boolean
}
