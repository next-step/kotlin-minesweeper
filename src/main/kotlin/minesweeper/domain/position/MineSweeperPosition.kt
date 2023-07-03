package minesweeper.domain.position

sealed class MineSweeperPosition

class MinePosition(private val position: Position) : MineSweeperPosition()

class EmptyPosition(private val position: Position, minePositions: Positions) : MineSweeperPosition() {

    private val aroundMineQuantity: Int

    init {
        aroundMineQuantity = position.aroundPositions().count { minePositions.contains(it) }
    }

    fun calculateAroundMineQuantity(): Int = aroundMineQuantity
}
