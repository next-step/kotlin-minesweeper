package domain

data class NormalCellProperty(private val aroundMineCount: AroundMineCount) : CellProperty() {
    override val type: CellType = CellType.NORMAL
    override fun getSymbol(): String {
        return CellType.symbolFromIsOpen(isOpen(), aroundMineCount.value.toString())
    }

    override fun isCleanAroundMineCount(): Boolean {
        return aroundMineCount.isClean()
    }
}
