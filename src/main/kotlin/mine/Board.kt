package mine

import mine.cell.Cells

class Board(
    val cells: Cells,
    val width: Width,
    val height: Height
) {
    // val width: Width
    // val height: Height

    companion object {
        fun createBoard(
            width: Width,
            height: Height,
            mine: Mine = Mine()
        ): Board {
            val size = width.value.times(height.value)
            require(size >= mine.value)

            return Board(Cells.createCells(width, height), width, height)
        }
    }
}
