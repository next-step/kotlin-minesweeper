package domain

interface CellProperty {
    val type: CellType
    fun getSymbol(): String

    companion object {
        fun of(isMine: Boolean, getAroundMineCount: () -> AroundMineCount): CellProperty {
            if (isMine) return MineCellProperty()
            val aroundMineCount = getAroundMineCount()
            return NormalCellProperty(aroundMineCount)
        }
    }
}
