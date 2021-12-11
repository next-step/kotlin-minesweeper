package domain

abstract class Slot(val isChecked: Boolean = false) {
    abstract fun isMine(): Boolean
}
