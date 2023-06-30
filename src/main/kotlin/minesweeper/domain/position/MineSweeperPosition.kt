package minesweeper.domain.position

class MineSweeperPosition(private val position: Position, private val type: PositionType) {

    fun isMine(): Boolean = PositionType.MINE == type
}
