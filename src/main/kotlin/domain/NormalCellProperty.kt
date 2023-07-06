package domain

class NormalCellProperty(private val aroundMineCount: AroundMineCount) : CellProperty {
    override val type: CellType = CellType.NORMAL
    override val isOpen: Boolean = false
    override fun getSymbol(): String {
        return aroundMineCount.value.toString()
    }

    override fun isCleanAroundMineCount(): Boolean {
        return aroundMineCount.isClean()
    }
}
