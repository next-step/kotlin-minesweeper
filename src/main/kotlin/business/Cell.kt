package business

class Cell(
    private val cellStatus: CellStatus = CellStatus.EMPTY,
    private val visibilityState: CardVisibilityState = CardVisibilityState.HIDDEN,
) {

    fun isMine(): Boolean = cellStatus.isMine()
    fun open(): Cell = Cell(cellStatus, CardVisibilityState.VISIBLE)

    fun isOpen(): Boolean = visibilityState.isVisible()
    fun isClear(): Boolean = visibilityState.isVisible() || cellStatus.isMine()
}
