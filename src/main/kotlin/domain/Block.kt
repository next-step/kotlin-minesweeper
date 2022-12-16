package domain

sealed interface Block {
    val position: Position
    fun isMine(): Boolean
}
