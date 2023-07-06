package minesweeper.domain.position

sealed class MineSweeperPosition(private val position: Position) {

    private var visit = false

    fun isVisit(): Boolean = visit

    fun visit() {
        visit = true
    }

    abstract fun isExistsMinesAround(): Boolean

    abstract fun isVisitablePosition(): Boolean
}

class MinePosition(position: Position) : MineSweeperPosition(position) {
    override fun isExistsMinesAround(): Boolean = true

    override fun isVisitablePosition(): Boolean = false
}

class EmptyPosition(position: Position, minePositions: Positions) : MineSweeperPosition(position) {

    private val aroundMineQuantity: Int

    init {
        aroundMineQuantity = position.aroundPositions().count { minePositions.contains(it) }
    }

    fun calculateAroundMineQuantity(): Int = aroundMineQuantity

    override fun isExistsMinesAround(): Boolean =
        calculateAroundMineQuantity() > NOT_EXISTS_MINE_QUANTITY

    override fun isVisitablePosition(): Boolean = !isVisit()

    companion object {
        private const val NOT_EXISTS_MINE_QUANTITY = 0
    }
}
