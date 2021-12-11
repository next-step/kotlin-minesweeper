package domain

class MineField(private val mines: List<Array<Slot>>) {

    fun isMine(x: Int, y: Int) = mines[x][y].isMine()

    fun isChecked(x: Int, y: Int) = mines[x][y].isChecked

    fun allSlots() = mines.map { it.toList() }

    fun nearMinesNumberAt(x: Int, y: Int) = mines[y][x].numberOfNearMines

    private fun findIndexsForNearMinesNums(size: FieldSize, x: Int, y: Int) {
        val startPoints = PointContainer(x.toStartPoint(), y.toStartPoint())
        val endPoints = PointContainer(x.toEndPoint(size.width - 1), y.toEndPoint(size.height - 1))
        setNearMineNums(startPoints, endPoints)
    }

    private fun setNearMineNums(
        startPoints: PointContainer,
        endPoints: PointContainer
    ) {
        (startPoints.x..endPoints.x)
            .flatMap { pointX -> makePointLists(pointX, startPoints.y, endPoints.y) }
            .forEach { setNumberOfNearMinesAtPoint(it) }
    }

    private fun setNumberOfNearMinesAtPoint(point: PointContainer) {
        if (!mines[point.y][point.x].isMine())
            mines[point.y][point.x] = Ground(nearMines = mines[point.y][point.x].numberOfNearMines + 1)
    }

    private fun makePointLists(pointX: Int, startPointY: Int, endPointY: Int) =
        (startPointY..endPointY).map { pointY -> PointContainer(pointX, pointY) }

    private fun Int.toStartPoint(): Int {
        if (this == 0)
            return this
        return this - 1
    }

    private fun Int.toEndPoint(lastIndex: Int): Int {
        if (this == lastIndex)
            return this
        return this + 1
    }

    companion object {
        fun createByIndexs(indexsForMines: List<Int>, size: FieldSize): MineField {
            val newMineField = MineField(List(size.height) { getRowFilledWithGround(size.width) })
            indexsForMines.forEach {
                val x = it % size.width
                val y = it / size.width
                newMineField.mines[y][x] = Mine()
                newMineField.findIndexsForNearMinesNums(size, x, y)
            }
            return newMineField
        }

        private fun getRowFilledWithGround(width: Int) = Array<Slot>(width) { Ground() }
    }
}
