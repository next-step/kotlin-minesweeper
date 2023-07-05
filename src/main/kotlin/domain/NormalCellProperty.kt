package domain

class NormalCellProperty(val aroundMineCount: AroundMineCount) : CellProperty {
    override val type: CellType = CellType.NORMAL
}
