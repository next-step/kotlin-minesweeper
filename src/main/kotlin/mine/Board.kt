package mine

import mine.cell.CellType
import mine.cell.Cells

/**
 * 게임 판 관리
 * */
class Board(
    val cells: Cells,
    val width: Width,
    val height: Height
) {

    companion object {
        fun createBoard(
            width: Width,
            height: Height,
            mine: Mine = Mine()
        ): Board {
            val size = width.value.times(height.value)
            require(size >= mine.value)


            val cells = Cells.createCells(width, height)
            cells.cellList.shuffled().mapIndexed { index, cell ->
                if (index < mine.value) {
                    cell.typeOf(CellType.MINE)
                    return@mapIndexed
                }
                cell.typeOf(CellType.NONE)
            }

            return Board(cells, width, height)
        }
    }
}
