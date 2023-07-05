package domain

class NormalCellProperty(val mineCountOfAround: MineCountNumber) : CellProperty {
    override val type: CellType = CellType.NORMAL
}
