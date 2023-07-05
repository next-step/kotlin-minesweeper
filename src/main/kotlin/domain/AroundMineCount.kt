package domain

import ErrorCode

class AroundMineCount(val value: Int) {
    init {
        require(value >= 0) { ErrorCode.INVALID_AROUND_MINE_COUNT_ERROR.msg }
    }
}
