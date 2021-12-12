package domain

class Mine(isChecked: Boolean = false, point: Point) : Slot(isChecked, SlotInfo(point, 0)) {
    override fun isMine() = true

    override fun setNumberOfNearMines(mineField: List<Slot>) = Unit
}
