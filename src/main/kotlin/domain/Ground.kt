package domain

class Ground(isChecked: Boolean = false) : Slot(isChecked) {
    override fun isMine() = false
}
