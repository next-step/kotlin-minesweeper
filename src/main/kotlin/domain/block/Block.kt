package domain.block

abstract class Block {

    private var isChecked: Boolean = false

    fun check() {
        check(!isChecked) { "이미 체크한 블록은 체크할 수 없습니다" }
        this.isChecked = true
    }

    fun isChecked(): Boolean = isChecked

    abstract fun isMine(): Boolean
    abstract fun isZero(): Boolean
    abstract fun surroundingMineCount(): SurroundingMineCount
}
