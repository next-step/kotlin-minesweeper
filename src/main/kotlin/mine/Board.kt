package mine

import mine.cell.Cells

/**
 * 게임 판 관리
 * */
class Board(
    val cells: Cells,
) {
    companion object {
        fun createBoard(
            width: Width,
            height: Height,
            mine: Mine = Mine()
        ): Board {
            val size = width.value.times(height.value)
            require(size >= mine.value)

            val cells = Cells.createCells(width, height, mine.value)
            return Board(cells)
        }
    }
}
