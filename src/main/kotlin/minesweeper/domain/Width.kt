package minesweeper.domain

class Width(val value: Int) {
    init {
        require(value >= MIN_WIDTH_VALUE) { "width는 $MIN_WIDTH_VALUE 이상만 허용됩니다." }
    }

    companion object {
        const val MIN_WIDTH_VALUE = 1
    }
}
