package domain

sealed interface Block {
    val position: Position
    val mineCount: MineCount

    fun isMine(): Boolean
    fun getMineCount(): Int = mineCount.value
}
