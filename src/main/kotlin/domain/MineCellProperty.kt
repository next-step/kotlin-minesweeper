package domain

class MineCellProperty : CellProperty {
    override val type: CellType = CellType.MINE
    override val isOpen: Boolean = false

    override fun getSymbol(): String {
        return CellType.MINE_SYMBOL
    }

    override fun isCleanAroundMineCount(): Boolean {
        return false
    }
}
