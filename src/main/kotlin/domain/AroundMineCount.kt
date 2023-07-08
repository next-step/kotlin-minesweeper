package domain

import ErrorCode

data class AroundMineCount(val value: Int) {
    fun isClean(): Boolean {
        return value == CLEAN_VALUE
    }
    init {
        require(value >= MINIMUM_VALUE) { ErrorCode.INVALID_AROUND_MINE_COUNT_ERROR.msg }
    }

    companion object {
        const val MINIMUM_VALUE = 0
        const val CLEAN_VALUE = 0
    }
}
