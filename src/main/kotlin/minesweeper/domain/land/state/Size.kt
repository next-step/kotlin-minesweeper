package minesweeper.domain.land.state

@JvmInline
value class Size(val value: Int) {
    init {
        require(value in MINIMUM_SIZE..MAXIMUM_SIZE) { "높이와 너비는 $MINIMUM_SIZE 이상 $MAXIMUM_SIZE 이하의 정수여야 합니다." }
    }

    constructor(value: String) : this(value.toIntOrNull() ?: throw IllegalArgumentException("숫자 이외의 값은 입력할 수 없습니다."))

    companion object {
        const val MINIMUM_SIZE = 1
        const val MAXIMUM_SIZE = 20
    }
}
