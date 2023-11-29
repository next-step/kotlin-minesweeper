package business

class Cell(private val cellStatus: CellStatus) {
    private var visibilityState = CardVisibilityState.HIDDEN

    fun isMine(): Boolean = cellStatus.isMine()
    fun open() {
        visibilityState = CardVisibilityState.VISIBLE
    }

    fun isOpen(): Any = visibilityState.isVisible()
}
