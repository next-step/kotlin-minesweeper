package domain.block

abstract class Block {
    abstract fun isMine(): Boolean
    abstract fun surroundingMineCount(): Int
}
