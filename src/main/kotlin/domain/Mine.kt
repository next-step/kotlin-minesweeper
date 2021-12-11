package domain

class Mine(isChecked: Boolean = false) : Slot(isChecked) {
    override fun isMine() = true
}
