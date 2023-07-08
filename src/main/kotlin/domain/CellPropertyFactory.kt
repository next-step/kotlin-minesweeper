package domain

class CellPropertyFactory {
    companion object {
        fun create(isMine: Boolean, getAroundMineCount: () -> AroundMineCount): CellProperty {
            if (isMine) return MineCellProperty()
            val aroundMineCount = getAroundMineCount()
            return NormalCellProperty(aroundMineCount)
        }
    }
}
