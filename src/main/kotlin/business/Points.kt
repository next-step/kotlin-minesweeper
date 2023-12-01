package business

class Points(private val pointList: List<Point>) {
    fun contains(point: Point): Boolean {
        return pointList.contains(point)
    }

    fun exclude(minePoints: Points): Points {
        return Points(pointList.filter { !minePoints.contains(it) })
    }

    fun toMineCells(): List<Cell> {
        return pointList.map { Cell(CellStatus.MINE, point = it) }
    }

    fun toNonMineCells(): List<Cell> {
        return pointList.map { Cell(CellStatus.EMPTY, point = it) }
    }
}
