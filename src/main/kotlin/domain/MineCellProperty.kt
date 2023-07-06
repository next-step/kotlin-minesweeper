package domain

class MineCellProperty : CellProperty {
    override val type: CellType = CellType.MINE
    override fun getSymbol(): String {
        return CellType.MINE_SYMBOL
    }
}
