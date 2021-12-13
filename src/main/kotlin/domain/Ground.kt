package domain

class Ground(isChecked: Boolean = false, point: Point, nearMines: Int = 0) :
    Slot(isChecked, SlotInfo(point, nearMines)) {
    override fun isMine() = false

    override fun setNumberOfNearMines(mines: List<Slot>) {
        val numberOfMines = mines.filter { isItInMineCheckRange(it.point()) }.size
        slotInfo.numberOfNearMines = numberOfMines
    }

    private fun isItInMineCheckRange(otherPoint: Point): Boolean {
        val isItXInRange = otherPoint.x.isItInRange(this.point().x)
        val isItYInRange = otherPoint.y.isItInRange(this.point().y)
        return isItXInRange && isItYInRange
    }

    private fun Int.isItInRange(p: Int) = this in p - 1..p + 1
}
