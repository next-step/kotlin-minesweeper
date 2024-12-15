package minesweeper.domain

class Vulture {
    fun plantMine(cell: Cell): Landmine = Landmine(row = cell.row(), column = cell.column())
}
