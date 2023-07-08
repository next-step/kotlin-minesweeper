package domain

abstract class CellProperty {
    private var state: CellState = CellState.HIDE
    abstract val type: CellType

    abstract fun getSymbol(): String
    abstract fun isCleanAroundMineCount(): Boolean
    fun isMine(): Boolean {
        return type == CellType.MINE
    }

    fun setOpen() {
        state = CellState.OPEN
    }

    fun isOpen(): Boolean {
        return state == CellState.OPEN
    }
}
