package domain

class NormalCellProperty(val aroundMineCount: AroundMineCount) : CellProperty {
    override val type: CellType = CellType.NORMAL
    override fun getSymbol(): String {
        return aroundMineCount.value.toString()
    }
}
