package domain

data class Mine(override val position: Position) : Block {
    override fun isMine(): Boolean = true
}
