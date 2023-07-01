package minesweeper.domain.board

class BoardRange(height: Int, width: Int) {

    private val heightRange: IntRange
    private val widthRange: IntRange
    init {
        require(height >= RANGE_MIN) { "높이는 1 이상이어야 합니다." }
        require(width >= RANGE_MIN) { "너비는 1 이상이어야 합니다." }

        heightRange = IntRange(RANGE_MIN, height)
        widthRange = IntRange(RANGE_MIN, width)
    }

    fun getRandomHeight(): Int {
        return heightRange.random()
    }

    fun getRandomWidth(): Int {
        return widthRange.random()
    }

    fun calculateArea(): Int = heightRange.max() * widthRange.max()

    fun maxHeight(): Int = heightRange.max()

    fun maxWidth(): Int = widthRange.max()

    companion object {
        private const val RANGE_MIN = 1
    }
}
