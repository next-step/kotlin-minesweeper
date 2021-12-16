package domain

class MineField(private val lines: List<MineLine>) {

    fun isMine(point: Point) = lines[point.y].isMineAt(point.x)

    fun isChecked(point: Point) = lines[point.y].isCheckedAt(point.x)

    fun allSlots() = lines.map { it.toList() }

    fun nearMinesNumberAt(point: Point) = lines[point.y].numberOfNearMinesAt(point.x)

    private fun setNearMines() {
        val allSlot = allSlots().flatten()
        val mines = allSlot.filter { it.isMine() }
        allSlot.filter { !it.isMine() }
            .forEach { it.setNumberOfNearMines(mines) }
    }

    companion object {
        fun createByIndexs(indexsForMines: Set<Point>, size: FieldSize): MineField {
            val newMineField = MineField(List(size.height) { createMineLine(it, size.width, indexsForMines) })
            newMineField.setNearMines()
            return newMineField
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
