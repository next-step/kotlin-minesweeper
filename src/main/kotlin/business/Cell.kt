package business

data class Cell(
    private val cellStatus: CellStatus = CellStatus.EMPTY,
    private val visibilityState: CardVisibilityState = CardVisibilityState.HIDDEN,
    val aroundMineCount: Int = INIT_VALUE,
    val point: Point = Point(INIT_VALUE, INIT_VALUE),
) {
    fun addAroundMineCount(): Cell = Cell(cellStatus, visibilityState, aroundMineCount + 1, point)
    fun isMine(): Boolean = cellStatus.isMine()
    fun open(): Cell = Cell(cellStatus, CardVisibilityState.VISIBLE, aroundMineCount, point)

    fun isOpen(): Boolean = visibilityState.isVisible()
    fun isClear(): Boolean = visibilityState.isVisible() || cellStatus.isMine()

    companion object {
        private const val INIT_VALUE = 0
        fun mine(point: Point): Cell = Cell(CellStatus.MINE, point = point)
        fun empty(point: Point): Cell = Cell(point = point)
    }
}
