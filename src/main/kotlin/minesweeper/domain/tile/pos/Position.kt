package minesweeper.domain.tile.pos

@JvmInline
value class Position(val value: Int) {
    init {
        require(value in MINIMUM_POSITION..MAXIMUM_POSITION) { "높이와 너비는 $MINIMUM_POSITION 이상 $MAXIMUM_POSITION 이하의 정수여야 합니다." }
    }

    constructor(value: String) : this(value.toIntOrNull() ?: throw IllegalArgumentException("숫자 이외의 값은 입력할 수 없습니다."))

    fun getCalibratedPosition() = value + CORRECTION_VALUE

    companion object {
        const val MINIMUM_POSITION = 0
        const val MAXIMUM_POSITION = 19
        private const val CORRECTION_VALUE = 1
    }
}
