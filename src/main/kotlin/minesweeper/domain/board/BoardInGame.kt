package minesweeper.domain.board

import minesweeper.domain.cell.Cells
import minesweeper.domain.position.Position

class BoardInGame(override val cells: Cells) : Board {

    override val isFinish = false

    override fun getResult() = BoardResult.Stopped(cells)

    override fun open(position: Position): Board {
        cells.open(position)

        return when {
            cells.isMine(position) -> BoardResult.Lose(cells)
            cells.isAllOpen() -> BoardResult.Win(cells)
            else -> this
        }
    }
}
