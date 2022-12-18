package minesweeper.domain.tile.pos

@JvmInline
value class Position(val value: Int) {
    init {
        require(value in MINIMUM_COORDINATE..MAXIMUM_COORDINATE) { "높이와 너비는 1 이상 20 이하의 정수여야 합니다." }
    }

    constructor(value: String) : this(value.toIntOrNull() ?: throw IllegalArgumentException("숫자 이외의 값은 입력할 수 없습니다."))

    companion object {
        const val MINIMUM_COORDINATE = 1
        const val MAXIMUM_COORDINATE = 20
    }
}
