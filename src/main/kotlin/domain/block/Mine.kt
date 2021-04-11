package domain.block

class Mine(isChecked: Boolean = false) : Block(isChecked) {

    override fun makeCheckedBlock(): Block {
        return Mine(true)
    }

    override fun isMine() = true

    override fun isZero() = false

    override fun surroundingMineCount() = throw UnsupportedOperationException("지뢰는 주변지뢰의 개수를 구할 수 없습니다.")

    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
