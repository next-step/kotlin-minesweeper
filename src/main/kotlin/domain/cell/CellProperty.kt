package domain.cell

abstract class CellProperty {
    private var state: CellState = CellState.HIDE

    abstract fun getSymbol(): String
    abstract fun isCleanAroundMineCount(): Boolean
    abstract fun isMine(): Boolean

    fun setOpen() {
        state = CellState.OPEN
    }

    fun isOpen(): Boolean {
        return state == CellState.OPEN
    }

    companion object {
        const val CLOSE_SYMBOL = "C"
    }
}
