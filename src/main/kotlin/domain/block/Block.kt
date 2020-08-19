package domain.block

interface Block {
    val position: Position

    fun isMine(): Boolean

    fun getMinesCount(): Int
}
