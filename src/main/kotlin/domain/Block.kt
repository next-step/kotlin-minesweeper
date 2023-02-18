package domain

interface Block {
    val position: Position
    val mineCount: MineCount
    val isVisible: Boolean

    fun getMineCount(): Int = mineCount.value
    fun isMine(): Boolean
    fun isZero(): Boolean = !isMine() && getMineCount() == 0
    fun isOpenable(): Boolean = !isVisible && !isMine()
    fun open(): Block
}
