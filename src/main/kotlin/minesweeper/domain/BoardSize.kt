package minesweeper.domain

class BoardSize(private val width: Int, private val height: Int) {

    init {
        require(value = width in BOARD_SIZE_RANGE && height in BOARD_SIZE_RANGE) {
            "점은 $BOARD_SIZE_RANGE 사이에 위치해야 합니다. 입력값 : $width, $height"
        }
    }

    fun area(): Int = width * height

    fun rangeWidth(): IntRange = START_INDEX until width

    fun rangeHeight(): IntRange = START_INDEX until height

    companion object {
        private const val START_INDEX: Int = 0
        private val BOARD_SIZE_RANGE: IntRange = 0..100
    }
}
