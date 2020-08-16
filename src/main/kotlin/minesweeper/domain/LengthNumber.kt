package minesweeper.domain

private const val MIN_SIZE = 1
private const val MAX_SIZE = 100
val NUMBER_REGEX = "(\\d{1,2})".toRegex()

class LengthNumber(val length: Int) {
    init {
        require(length in MIN_SIZE..MAX_SIZE) { "높이와 너비는 $MIN_SIZE ~ $MAX_SIZE 사이의 숫자를 입력해주세요" }
    }

    constructor(lengthNumber: String) : this(
        lengthNumber.toInt()
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LengthNumber

        if (length != other.length) return false

        return true
    }

    override fun hashCode(): Int {
        return length
    }
}
