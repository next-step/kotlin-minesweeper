package business

class Cells(private val cells: List<List<Cell>>) {
    private operator fun get(point: Point): Cell = cells[point.x][point.y]

    fun isMine(point: Point): Boolean = this[point].isMine()
    fun isOpen(point: Point): Boolean = this[point].isOpen()

    fun open(point: Point) {
        this[point].open()
        if (isSafePoint(point)) openAround(point)
    }

    fun countMines(point: Point): Int = point.aroundPoints()
        .filter { isValidPoint(it) }
        .count { isMine(it) }
    private fun isSafePoint(point: Point) = countMines(point) == Board.SAFE_MINE_COUNT

    private fun openAround(point: Point) = point.aroundPoints()
        .filter { isValidPoint(it) }
        .filter { !isOpen(it) }
        .forEach { open(it) }

    private fun isValidPoint(point: Point): Boolean = point.x in cells.indices && point.y in cells[FIRST_INDEX].indices

    companion object {
        const val FIRST_INDEX = 0
    }
}
