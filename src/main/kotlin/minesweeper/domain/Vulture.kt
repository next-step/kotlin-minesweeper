package minesweeper.domain

class Vulture {
    fun plantMine(cell: Cell): Landmine = Landmine(row = cell.location().row, column = cell.location().column)
}
