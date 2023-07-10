package domain

import ErrorCode

@JvmInline
value class AroundMineCount(val value: Int) {
    val isClean: Boolean
        get() = (value == CLEAN_VALUE)

    init {
        require(value >= MINIMUM_VALUE) { ErrorCode.INVALID_AROUND_MINE_COUNT_ERROR.msg }
    }

    companion object {
        const val MINIMUM_VALUE = 0
        const val CLEAN_VALUE = 0
    }
}
