package domain.block

sealed interface Block {
    fun isMine(): Boolean
}
