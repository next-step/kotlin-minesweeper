package minesweeper.domain

class NonMineCellState(
    private val mineCount: Int = 0,
) : CellState(CellType.NON_MINE) {

    override fun isBomb() = false

    override fun isNonMine() = true

    override fun getNearMineCount() = mineCount
}
