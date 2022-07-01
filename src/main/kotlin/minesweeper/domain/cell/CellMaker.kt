package minesweeper.domain.cell

interface CellMaker {
    fun make(width: Int, height: Int, numberOfMines: Int): List<Cell>
}
