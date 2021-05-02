package domain.block

abstract class Block(private val isChecked: Boolean = false) {

    fun isChecked(): Boolean = isChecked

    fun check(): Block {
        check(!isChecked) { "이미 체크한 블록은 체크할 수 없습니다" }
        return makeCheckedBlock()
    }

    protected abstract fun makeCheckedBlock(): Block

    abstract fun isMine(): Boolean
    abstract fun isZero(): Boolean
    abstract fun getSurroundingMineCount(): Int
}
