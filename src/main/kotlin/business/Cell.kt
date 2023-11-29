package business

class Cell(private val cellStatus: CellStatus, visibilityState: CardVisibilityState = CardVisibilityState.HIDDEN) {
    private var _visibilityState = visibilityState

    fun isMine(): Boolean = cellStatus.isMine()
    fun open() {
        _visibilityState = CardVisibilityState.VISIBLE
    }

    fun isOpen(): Boolean = _visibilityState.isVisible()
    fun isClear(): Boolean = _visibilityState.isVisible() || cellStatus.isMine()
}
