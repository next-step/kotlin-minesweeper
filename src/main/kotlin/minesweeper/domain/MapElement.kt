package minesweeper.domain

sealed class MapElement {
    private var covered: Boolean = true

    fun open() {
        covered = false
    }

    fun isCovered(): Boolean {
        return covered
    }

    override fun equals(other: Any?): Boolean {
        if (other !is MapElement) {
            return false
        }
        return covered == other.covered
    }

    override fun hashCode(): Int {
        return covered.hashCode()
    }
}

class MineMapElement : MapElement() {
    override fun equals(other: Any?): Boolean {
        if (other !is MineMapElement) {
            return false
        }
        return super.equals(other)
    }
}

class NumberMapElement(val value: Int) : MapElement() {
    init {
        require(value in MIN_MINE_COUNT..MAX_MINE_COUNT) { INVALID_VALUE_ERROR_MESSAGE }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is NumberMapElement) {
            return false
        }
        return super.equals(other) && this.value == other.value
    }

    override fun hashCode(): Int {
        return super.hashCode() * 31 + value
    }

    companion object {
        private const val MIN_MINE_COUNT = 0
        private const val MAX_MINE_COUNT = 8
        private const val INVALID_VALUE_ERROR_MESSAGE = "지뢰 숫자는 $MIN_MINE_COUNT ~ ${MAX_MINE_COUNT}사이여야 합니다"
    }
}
