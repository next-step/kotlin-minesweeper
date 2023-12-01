package business

data class Cell(
    private val cellStatus: CellStatus = CellStatus.EMPTY,
    private val visibilityState: CardVisibilityState = CardVisibilityState.HIDDEN,
    val aroundMineCount: Int = 0,
) {
    fun addAroundMineCount(): Cell = Cell(cellStatus, visibilityState, aroundMineCount + 1)
    fun isMine(): Boolean = cellStatus.isMine()
    fun open(): Cell = Cell(cellStatus, CardVisibilityState.VISIBLE, aroundMineCount)

    fun isOpen(): Boolean = visibilityState.isVisible()
    fun isClear(): Boolean = visibilityState.isVisible() || cellStatus.isMine()

    companion object {
        fun mine(): Cell = Cell(CellStatus.MINE)
        fun empty(): Cell = Cell()
    }
}
