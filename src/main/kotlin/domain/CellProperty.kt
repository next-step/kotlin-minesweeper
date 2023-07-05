package domain

interface CellProperty {
    val type: CellType

    companion object {
        fun of(isMine: Boolean, aroundMineCount: AroundMineCount): CellProperty {
            return when (isMine) {
                true -> MineCellProperty()
                false -> NormalCellProperty(aroundMineCount)
            }
        }
    }
}
