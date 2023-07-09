package domain.cell

data class MineCellProperty(var state: CellState = CellState.HIDE) : CellProperty() {

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
