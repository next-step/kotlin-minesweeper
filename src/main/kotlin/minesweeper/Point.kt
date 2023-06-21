package minesweeper

@JvmInline
value class Point(val value: Int) {

    init {
        require(value in POINT_RANGE) {
            "점은 $POINT_RANGE 사이에 위치해야 합니다. 입력값 : $value"
        }
    }

    companion object {
        private val POINT_RANGE: IntRange = 1..100
    }
}
