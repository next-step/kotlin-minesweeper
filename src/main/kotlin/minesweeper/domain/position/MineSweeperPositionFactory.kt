package minesweeper.domain.position

object MineSweeperPositionFactory {

    fun createEmptyPosition(position: Position): MineSweeperPosition =
        MineSweeperPosition(position = position, type = PositionType.EMPTY)

    fun createMinePosition(position: Position): MineSweeperPosition =
        MineSweeperPosition(position = position, type = PositionType.MINE)
}
