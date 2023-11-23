package minesweeper.model

import minesweeper.model.board.Minefield
import minesweeper.model.cell.Opening

class MineSweeperGame(
    private val countOfMine: Int,
    private val minefield: Minefield
) {
    constructor(rows: Int, cols: Int, countOfMine: Int) : this(
        countOfMine = countOfMine,
        minefield = Minefield(rows, cols)
    )

    init {
        plantingMines()
    }

    fun minefield(): Array<Array<Opening>> = minefield.minefield

    private fun plantingMines() = minefield.plantingMine(countOfMine)
}
