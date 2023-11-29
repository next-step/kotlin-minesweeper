package business

class Board(cells: List<List<Cell>>) {
    private val _cells: List<List<Cell>> = cells
    fun isMine(point: Point): Boolean = _cells[point.x][point.y].isMine()
}
