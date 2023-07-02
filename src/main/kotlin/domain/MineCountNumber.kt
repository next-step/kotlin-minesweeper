package domain

class MineCountNumber(injectValue: PositiveNumber, height: PositiveNumber, width: PositiveNumber) {
    val value: Int

    init {
        require(injectValue.value <= height.value * width.value) { ErrorCode.INVALID_MINE_COUNT_NUMBER_ERROR.msg }
        value = injectValue.value
    }
}
