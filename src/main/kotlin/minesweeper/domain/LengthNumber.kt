package minesweeper.domain

const val MIN_SIZE = 1
const val MAX_SIZE = 100

class LengthNumber(val length: Int) {
    init {
        require(length in MIN_SIZE..MAX_SIZE) { "높이와 너비는 $MIN_SIZE ~ $MAX_SIZE 사이의 숫자를 입력해주세요" }
    }
}
