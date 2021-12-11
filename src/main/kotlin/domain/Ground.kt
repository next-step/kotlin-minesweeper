package domain

class Ground(isChecked: Boolean) : Slot(isChecked) {
    override fun isMine() = false
}
