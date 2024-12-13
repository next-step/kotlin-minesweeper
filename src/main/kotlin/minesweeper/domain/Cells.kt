package minesweeper.domain

open class Cells(private val cells: List<Column>) : List<Column> by cells {
    constructor(width: Width, height: Height) : this(List<Column>(height.value) { Column(width) })

    val width: Width
        get() = Width(cells.size)
    val height: Height
        get() = Height(cells[FIRST_COLUMN_INDEX].size)

    fun setCellStatus(
        location: Location,
        cellState: CellState,
    ) {
        require(location.y < width.value) { ERROR_INVALID_POS }
        require(location.x < height.value) { ERROR_INVALID_POS }
        cells[location.y][location.x].changeState(cellState)
    }

    companion object {
        private const val ERROR_INVALID_POS = "좌표의 범위가 잘못되었습니다"
        private const val FIRST_COLUMN_INDEX = 0
    }
}
