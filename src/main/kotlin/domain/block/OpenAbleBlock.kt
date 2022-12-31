package domain.block

sealed interface OpenAbleBlock : Block {
    fun isOpened(): Boolean

    fun openBlock()
}
