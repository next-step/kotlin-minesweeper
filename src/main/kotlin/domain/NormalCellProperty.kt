package domain

class NormalCellProperty(private val mineCountOfAround: MineCountNumber) : CellProperty {
    fun getMineCountOfAround(): MineCountNumber {
        return mineCountOfAround
    }
    override val type: CellType = CellType.NORMAL
}
