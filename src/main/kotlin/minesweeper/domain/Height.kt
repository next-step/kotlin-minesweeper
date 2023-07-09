package minesweeper.domain

class Height(val value: Int) {
    init {
        require(value >= MIN_HEIGHT_VALUE) { "height는 $MIN_HEIGHT_VALUE 이상만 허용됩니다." }
    }

    companion object {
        const val MIN_HEIGHT_VALUE = 1
    }
}
