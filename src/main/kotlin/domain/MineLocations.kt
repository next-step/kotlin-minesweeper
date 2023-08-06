package domain

data class MineLocations(
    val points: Set<Point>
) {
    constructor(vararg point: Point) : this(points = point.toSet())

    fun layoutWithMines(boardSize: BoardSize): Layout {
        val layout = Layout(boardSize)
        points.forEach { point ->
            layout[point.y][point.x] = Cell(CellStatus.MINE)
        }
        return layout
    }
}
