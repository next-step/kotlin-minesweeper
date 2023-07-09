package domain.cell

import domain.AroundMineCount

data class NormalCellProperty(private val aroundMineCount: AroundMineCount) : CellProperty() {
    override fun getSymbol(): String {
        return if (isOpen()) aroundMineCount.value.toString() else CLOSE_SYMBOL
    }

    override fun isMine(): Boolean {
        return false
    }

    override fun isCleanAroundMineCount(): Boolean {
        return aroundMineCount.isClean
    }
}
