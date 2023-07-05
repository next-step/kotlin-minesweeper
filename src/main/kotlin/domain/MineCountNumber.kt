package domain

class MineCountNumber(val value: Int, height: PositiveNumber, width: PositiveNumber) {

    init {
        require(value <= height.value * width.value) { ErrorCode.INVALID_MINE_COUNT_NUMBER_ERROR.msg }
    }
}
