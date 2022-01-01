package minesweeper.domain.board

import minesweeper.domain.cell.Cells
import minesweeper.domain.position.Position

sealed interface Board {

    val cells: Cells

    val isFinish: Boolean

    fun getResult(): BoardResult

    fun open(position: Position): Board

    fun flag(position: Position): Board
}
