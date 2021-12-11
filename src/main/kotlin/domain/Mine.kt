package domain

class Mine(isChecked: Boolean = false) : Slot(isChecked, 0) {
    override fun isMine() = true
}
