package domain

class Ground(isChecked: Boolean = false, nearMines: Int = 0) : Slot(isChecked, nearMines) {
    override fun isMine() = false
}
