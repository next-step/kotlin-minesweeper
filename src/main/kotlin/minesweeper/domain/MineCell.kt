package minesweeper.domain

data class MineCell(override val position: Position, override val openState: OpenState = OpenState.CLOSED) : Cell() {
    override fun isMine(): Boolean = true
    override fun open(): MineCell = MineCell(position, OpenState.OPENED)
    override fun isOpen(): Boolean = openState == OpenState.OPENED
}
