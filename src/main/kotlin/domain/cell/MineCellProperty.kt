package domain.cell

data class MineCellProperty(override val type: CellType = CellType.MINE) : CellProperty() {

    override fun getSymbol(): String {
        return CellType.symbolFromIsOpen(isOpen())
    }

    override fun isCleanAroundMineCount(): Boolean {
        return false
    }
}
