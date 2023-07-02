package minesweeper.domain.position

sealed class MineSweeperPosition

class MinePosition(private val position: Position) : MineSweeperPosition()

class EmptyPosition(private val position: Position) : MineSweeperPosition()
