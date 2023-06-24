package minesweeper.domain

@JvmInline
value class PositiveNumber(val value: Int) {

    init {
        require(value = value > MINIMUM_NUMBER) {
            "숫자는 ${MINIMUM_NUMBER}보다 큰 값을 입력해야 합니다. 입력값 : $value"
        }
    }

    companion object {
        private const val MINIMUM_NUMBER: Int = 0
    }
}
