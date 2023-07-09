package domain.cell

import domain.AroundMineCount

class CellPropertyFactory {
    companion object {
        fun create(isMine: Boolean, getAroundMineCount: () -> AroundMineCount): CellProperty {
            if (isMine) return MineCellProperty()
            val aroundMineCount = getAroundMineCount()
            return NormalCellProperty(aroundMineCount)
        }
    }
}

private data class MineCellProperty(override var state: CellState = CellState.HIDE) : CellProperty() {

    override fun getSymbol(): String {
        return if (isOpen()) MINE_SYMBOL else CLOSE_SYMBOL
    }

    override fun isMine(): Boolean {
        return true
    }

    override fun isCleanAroundMineCount(): Boolean {
        return false
    }

    companion object {
        private const val MINE_SYMBOL = "*"
    }
}

private data class NormalCellProperty(private val aroundMineCount: AroundMineCount, override var state: CellState = CellState.HIDE) : CellProperty() {
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

