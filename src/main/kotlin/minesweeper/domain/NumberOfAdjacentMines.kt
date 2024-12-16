package minesweeper.domain

@JvmInline
value class NumberOfAdjacentMines(private val value: Int) {
    init {
        require(value in MIN_NUMBER_OF_ADJACENT_MINES..MAX_NUMBER_OF_ADJACENT_MINES) {
            "인접 지뢰 개수는 $MIN_NUMBER_OF_ADJACENT_MINES 에서 $MAX_NUMBER_OF_ADJACENT_MINES 사이의 수만 가능합니다: value=$value"
        }
    }

    operator fun inc(): NumberOfAdjacentMines = NumberOfAdjacentMines(value + 1)

    companion object {
        private const val MAX_NUMBER_OF_ADJACENT_MINES = 8
        private const val MIN_NUMBER_OF_ADJACENT_MINES = 0
        val ZERO = NumberOfAdjacentMines(0)
    }
}
