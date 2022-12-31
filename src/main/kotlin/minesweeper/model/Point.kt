package minesweeper.model

data class Point(val x: Int, val y: Int) {
    init {
        require(x >= MIN_VALUE) { "x 좌표는 $MIN_VALUE 이상이어야 합니다." }
        require(y >= MIN_VALUE) { "y 좌표는 $MIN_VALUE 이상이어야 합니다." }
    }

    companion object {
        const val MIN_VALUE = 0
    }
}
