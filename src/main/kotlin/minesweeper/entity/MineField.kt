package minesweeper.entity

class MineField(
    val height: Height,
    val width: Width,
    private val _cells: Cells,
) {
    init {
        require(cells.size == height.value * width.value) {
            "Cells의 개수가 보드 크기와 일치하지 않습니다."
        }
    }

    val cells: List<Cell>
        get() = _cells.cells.toList()

    fun findCell(coordinate: Coordinate): Cell {
        return _cells.findCell(coordinate)
    }
}
