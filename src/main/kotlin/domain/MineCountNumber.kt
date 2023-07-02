package domain

class MineCountNumber(val value: Int, height: PositiveNumber, width: PositiveNumber) {

    init {
        if (value != 0) PositiveNumber(value)
        require(value <= height.value * width.value) { ErrorCode.INVALID_MINE_COUNT_NUMBER_ERROR.msg }
    }
}
