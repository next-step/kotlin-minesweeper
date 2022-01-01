package minesweeper.domain.board

import minesweeper.domain.cell.Cells
import minesweeper.domain.position.Position

sealed class BoardResult(override val cells: Cells) : Board {

    final override val isFinish = true

    final override fun getResult() = this

    final override fun open(position: Position): Board {
        throw IllegalStateException("완료된 게임입니다.")
    }

    final override fun flag(position: Position): Board {
        throw IllegalStateException("완료된 게임입니다.")
    }

    class Win(cells: Cells) : BoardResult(cells)
    class Lose(cells: Cells) : BoardResult(cells)
    class Stopped(cells: Cells) : BoardResult(cells)
}
