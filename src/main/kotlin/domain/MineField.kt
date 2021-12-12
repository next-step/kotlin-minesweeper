package domain

class MineField(private val lines: List<MineLine>) {

    fun isMine(point: Point) = lines[point.y].isMineAt(point.x)

    fun isChecked(point: Point) = lines[point.y].isCheckedAt(point.x)

    fun allSlots() = lines.map { it.toList() }

    private fun changeToMineAt(point: Point) = lines[point.y].changeToMineAt(point)

    fun nearMinesNumberAt(point: Point) = lines[point.y].numberOfNearMinesAt(point.x)

    companion object {
        fun createByIndexs(indexsForMines: List<Point>, size: FieldSize): MineField {
            val newMineField = MineField(List(size.height) { createMineLine(it, size.width) })
            indexsForMines.forEach(newMineField::changeToMineAt)

            val allSlot = newMineField.allSlots()
                .flatten()
            val mines = allSlot.filter { it.isMine() }
            allSlot.filter { !it.isMine() }
                .forEach {
                    it.setNumberOfNearMines(mines)
                }
            return newMineField
        }

        private fun createMineLine(height: Int, width: Int) =
            MineLine(Array(width) { Ground(point = Point(it, height)) })
    }
}
