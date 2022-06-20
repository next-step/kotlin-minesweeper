package minesweeper.domain

@JvmInline
value class CoordinateIndex(private val value: Int) {
    init {
        require(value >= START_INDEX) {
            "좌표 값은 $START_INDEX 이상이어야 합니다"
        }
    }

    companion object {
        const val START_INDEX = 0
    }
}
