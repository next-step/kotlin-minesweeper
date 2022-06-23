package minesweeper.domain.board

import minesweeper.domain.cell.Mine

interface MineMaker {

    fun createMines(width: Int, height: Int, numberOfMines: Int): List<Mine>
}
