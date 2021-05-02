package domain.block

abstract class Block(private val isOpened: Boolean = false) {

    fun isOpened(): Boolean = isOpened

    fun open(): Block {
        check(!isOpened) { "이미 오픈한 블록은 오픈할 수 없습니다" }
        return makeOpenedBlock()
    }

    protected abstract fun makeOpenedBlock(): Block

    abstract fun isMine(): Boolean
    abstract fun isZero(): Boolean
    abstract fun getSurroundingMineCount(): Int
}
