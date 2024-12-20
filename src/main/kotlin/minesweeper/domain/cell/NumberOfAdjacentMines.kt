package minesweeper.domain.cell

@JvmInline
value class NumberOfAdjacentMines(private val value: Int) {
    init {
        require(value in MIN_VALUE..MAX_VALUE) {
            "인접 지뢰 개수는 $MIN_VALUE 에서 $MAX_VALUE 사이의 수만 가능합니다: value=$value"
        }
    }

    operator fun inc(): NumberOfAdjacentMines = NumberOfAdjacentMines(value + 1)

    operator fun compareTo(numberOfAdjacentMines: NumberOfAdjacentMines): Int {
        return value - numberOfAdjacentMines.value
    }

    companion object {
        private const val MIN_VALUE = 0
        private const val MAX_VALUE = 8
        val ZERO = NumberOfAdjacentMines(0)
    }
}
