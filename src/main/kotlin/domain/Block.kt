package domain

interface Block {
    val position: Position
    val mineCount: MineCount
    val visible: Boolean

    fun getMineCount(): Int = mineCount.value
    fun isMine(): Boolean
    fun isZero(): Boolean = !isMine() && getMineCount() == 0
    fun isOpenable(): Boolean = !visible && !isMine()
    fun open(): Block
}
