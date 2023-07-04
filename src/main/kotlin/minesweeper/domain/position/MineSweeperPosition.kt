package minesweeper.domain.position

sealed class MineSweeperPosition(private val position: Position) {

    private var visit = false

    fun isVisit(): Boolean = visit

    fun visit() {
        visit = true
    }
}

class MinePosition(position: Position) : MineSweeperPosition(position)

class EmptyPosition(position: Position, minePositions: Positions) : MineSweeperPosition(position) {

    private val aroundMineQuantity: Int

    init {
        aroundMineQuantity = position.aroundPositions().count { minePositions.contains(it) }
    }

    fun calculateAroundMineQuantity(): Int = aroundMineQuantity
}
