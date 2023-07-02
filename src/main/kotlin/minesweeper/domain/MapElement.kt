package minesweeper.domain

sealed class MapElement(protected open var covered: Boolean = true) {

    fun open() {
        covered = false
    }

    fun isCovered(): Boolean {
        return covered
    }
}

data class MineMapElement(override var covered: Boolean = true) : MapElement()

data class NumberMapElement(val value: Int) : MapElement() {
    init {
        require(value in MIN_MINE_COUNT..MAX_MINE_COUNT) { INVALID_VALUE_ERROR_MESSAGE }
    }

    companion object {
        private const val MIN_MINE_COUNT = 0
        private const val MAX_MINE_COUNT = 8
        private const val INVALID_VALUE_ERROR_MESSAGE = "지뢰 숫자는 $MIN_MINE_COUNT ~ ${MAX_MINE_COUNT}사이여야 합니다"
    }
}
