package domain

class MineField(
    private val lines: List<MineLine>,
    private val fieldSize: FieldSize
) {

    fun isMine(point: Point) = lines[point.y].isMineAt(point.x)

    fun isChecked(point: Point) = lines[point.y].isCheckedAt(point.x)

    fun changeChecked(point: Point) = lines[point.y].changeCheckedAt(point.x)

    fun allSlots() = lines.map { it.toList() }

    fun changeNearZeroSlots(point: Point) {
        if (isChecked(point) || isMine(point))
            return
        changeChecked(point)
        if (point.y - 1 >= 0)
            changeNearZeroSlots(Point(point.x, point.y - 1))
        if (point.y + 1 < fieldSize.height)
            changeNearZeroSlots(Point(point.x, point.y + 1))
        if (point.x - 1 >= 0)
            changeNearZeroSlots(Point(point.x - 1, point.y))
        if (point.x + 1 < fieldSize.width)
            changeNearZeroSlots(Point(point.x + 1, point.y))
    }

    fun nearMinesNumberAt(point: Point) = lines[point.y].numberOfNearMinesAt(point.x)

    fun setNearMines() {
        val allSlot = allSlots().flatten()
        val mines = allSlot.filter { it.isMine() }
        allSlot.filter { !it.isMine() }
            .forEach { it.setNumberOfNearMines(mines) }
    }

    companion object {
        fun createByIndexs(indexsForMines: Set<Point>, size: FieldSize): MineField {
            return MineField(List(size.height) { createMineLine(it, size.width, indexsForMines) }, size)
        }

        private fun createMineLine(yIndex: Int, width: Int, indexsForMines: Set<Point>): MineLine {
            val line = List(width) { setSlotType(Point(it, yIndex), indexsForMines) }
            return MineLine(line)
        }

        private fun setSlotType(currentPoint: Point, indexsForMines: Set<Point>): Slot {
            if (indexsForMines.contains(currentPoint))
                return Mine(point = currentPoint)
            return Ground(point = currentPoint)
        }
    }
}
