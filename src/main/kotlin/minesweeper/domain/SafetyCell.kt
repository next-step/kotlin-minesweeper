package minesweeper.domain

data class SafetyCell(override val position: Position, val mineCount: Int, override val openState: OpenState = OpenState.CLOSED) : Cell() {
    override fun isMine(): Boolean = false
    override fun open(): SafetyCell = SafetyCell(position, mineCount, OpenState.OPENED)
    override fun isOpen(): Boolean = openState == OpenState.OPENED
    fun isNotContainAroundMine(): Boolean = mineCount == 0
}
