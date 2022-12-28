package domain.block

sealed class Block {
    abstract fun availableOpen(): Boolean
    abstract fun isMine(): Boolean
    abstract fun openBlock()
}
