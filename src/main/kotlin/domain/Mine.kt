package domain

class Mine(isChecked: Boolean) : Slot(isChecked) {
    override fun isMine() = true
}
