package tdd.minesweeper.domain

@JvmInline
value class AdjacentMines(private val value: Int) : Comparable<AdjacentMines> {
    init {
        require(value in MIN_VALUE..MAX_VALUE) {
            "인접 지뢰 수는 $MIN_VALUE ~ $MAX_VALUE 만 허용한다: valuer=$value"
        }
    }

    fun inc(): AdjacentMines {
        return AdjacentMines(value + 1)
    }

    override operator fun compareTo(other: AdjacentMines): Int = value.compareTo(other.value)

    companion object {
        private const val MIN_VALUE = 0
        private const val MAX_VALUE = 8
    }
}
