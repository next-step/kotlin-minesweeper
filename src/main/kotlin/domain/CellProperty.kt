package domain

interface CellProperty {
    val type: CellType
    val isOpen: Boolean
    fun getSymbol(): String
    fun isCleanAroundMineCount(): Boolean

    companion object {
        fun of(isMine: Boolean, getAroundMineCount: () -> AroundMineCount): CellProperty {
            if (isMine) return MineCellProperty()
            val aroundMineCount = getAroundMineCount()
            return NormalCellProperty(aroundMineCount)
        }
    }
}
