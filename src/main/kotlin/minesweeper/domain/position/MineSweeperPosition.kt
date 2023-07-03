package minesweeper.domain.position

sealed class MineSweeperPosition(private val position: Position) {

    fun isSamePosition(position: Position): Boolean = position == this.position
}

class MinePosition(position: Position) : MineSweeperPosition(position)

class EmptyPosition(position: Position, minePositions: Positions) : MineSweeperPosition(position) {

    private val aroundMineQuantity: Int

    init {
        aroundMineQuantity = position.aroundPositions().count { minePositions.contains(it) }
    }

    fun calculateAroundMineQuantity(): Int = aroundMineQuantity
}
