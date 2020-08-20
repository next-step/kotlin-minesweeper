package minesweeper.domain

class XPosition(val x: Int) {
    companion object {
        private val xPositions: Map<Int, XPosition> by lazy {
            (MIN_SIZE..MAX_SIZE).associateWith { XPosition(it) }
        }
        fun of(number: Int): XPosition {
            return xPositions[number] ?: throw IllegalArgumentException("$MIN_SIZE~$MAX_SIZE 사이의 숫자를 입력해 주세요.")
        }
    }
}
