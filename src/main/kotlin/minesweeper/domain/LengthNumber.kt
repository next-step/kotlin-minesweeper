package minesweeper.domain

private const val MIN_SIZE = 1
private const val MAX_SIZE = 100
val NUMBER_REGEX = "(\\d{1,2})".toRegex()

data class LengthNumber(val length: Int) {
    init {
        require(length in MIN_SIZE..MAX_SIZE) { "높이와 너비는 $MIN_SIZE ~ $MAX_SIZE 사이의 숫자를 입력해주세요" }
    }

    constructor(lengthNumber: String) : this(
        lengthNumber.toInt()
    )
}
