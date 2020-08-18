package domain

interface Block {
    val position: Position

    fun isMine(): Boolean

    fun getMinesCount(): Int
}
