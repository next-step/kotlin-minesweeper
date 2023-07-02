package domain

interface CellProperty {
    val type: CellType

    companion object {
        fun of(isMine: Boolean, mineCountInPosition: MineCountNumber): CellProperty {
            return when (isMine) {
                true -> MineCellProperty()
                false -> NormalCellProperty(mineCountInPosition)
            }
        }
    }
}
