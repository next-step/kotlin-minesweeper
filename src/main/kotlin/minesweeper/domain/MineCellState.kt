package minesweeper.domain

class MineCellState : CellState(CellType.MINE) {

    override fun isBomb() = isOpen

    override fun isNonMine() = false

    override fun getNearMineCount(): Int = 0
}
