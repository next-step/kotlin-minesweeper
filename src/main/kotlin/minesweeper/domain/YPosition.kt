package minesweeper.domain

data class YPosition(val value: Int) {
    companion object {
        private val yPositions: Map<Int, YPosition> by lazy {
            (MIN_SIZE..MAX_SIZE).associateWith { YPosition(it - 1) }
        }

        fun of(number: Int): YPosition {
            return yPositions[number] ?: throw IllegalArgumentException("$MIN_SIZE~$MAX_SIZE 사이의 숫자를 입력해 주세요.")
        }
    }
}
