package mine

import mine.cell.Cells
import mine.cell.MineCell

/**
 * 게임 판 관리
 * */
class Board(
    val cells: Cells,
) {
    val width: Width = cells.row().plus(1).let(::Width)
    val height: Height = cells.column().plus(1).let(::Height)

    companion object {
        fun createBoard(
            width: Width,
            height: Height,
            mine: Mine = Mine()
        ): Board {
            val size = width.value.times(height.value)
            require(size >= mine.value)

            val cells = Cells.createCells(width, height)
            cells.values.shuffled().mapIndexed { index, cell ->
                if (index < mine.value) {
                    MineCell(cell.position)
                    return@mapIndexed
                }
            }

            return Board(cells)
        }
    }
}
