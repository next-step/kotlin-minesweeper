package domain

import domain.ExceptionTypes.SLOT_NOT_FOUND_AT_POINT

class MineField(
    private val mineMap: Map<Point, Slot>,
    private val fieldSize: FieldSize
) {

    fun isMine(point: Point) = getSlotByPoint(point).isMine()

    fun isChecked(point: Point) = getSlotByPoint(point).isChecked()

    fun changeChecked(point: Point) = getSlotByPoint(point).changeChecked()

    fun nearMinesNumberAt(point: Point) = getSlotByPoint(point).numberOfNearMines()

    fun allSlots() = mineMap.map { it.value }

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

    fun setNearMines() {
        val allSlot = allSlots()
        val mines = allSlot.filter { it.isMine() }
        allSlot.filter { !it.isMine() }
            .forEach { it.setNumberOfNearMines(mines) }
    }

    private fun getSlotByPoint(point: Point): Slot {
        checkPointOverSize(point)
        val currentSlot = mineMap[point]
        require(currentSlot != null) { SLOT_NOT_FOUND_AT_POINT }
        return currentSlot
    }

    private fun checkPointOverSize(point: Point) {
        require(point.x < fieldSize.width) { ExceptionTypes.SLOT_CHECK_REQUEST_NOT_OVER_SIZE }
        require(point.y < fieldSize.height) { ExceptionTypes.SLOT_CHECK_REQUEST_NOT_OVER_SIZE }
    }

    companion object {
        fun createByIndexs(indexsForMines: Set<Point>, size: FieldSize): MineField {
            return MineField(createMineMap(indexsForMines, size), size)
        }

        private fun createMineMap(indexsForMines: Set<Point>, size: FieldSize): Map<Point, Slot> =
            (0 until size.width * size.height)
                .map { Point(it % size.width, it / size.width) }
                .associateWith { setSlotType(it, indexsForMines) }

        private fun setSlotType(currentPoint: Point, indexsForMines: Set<Point>): Slot {
            if (indexsForMines.contains(currentPoint))
                return Mine(point = currentPoint)
            return Ground(point = currentPoint)
        }
    }
}
