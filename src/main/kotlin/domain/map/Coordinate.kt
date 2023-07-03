package domain.map

data class Coordinate(
    val x: Int,
    val y: Int,
) {

    init {
        require(x >= 0 && y >= 0) {
            "coordinate must be greater than or equal to zero. your input : x - $x, y - $y"
        }
    }

    fun aroundCoordinates(): Set<Coordinate> {
        val left = leftOrNull()
        val leftTop = left?.topOrNull()
        val top = topOrNull()
        val rightTop = top?.rightOrNull()
        val right = rightOrNull()
        val rightBottom = right?.bottomOrNull()
        val bottom = bottomOrNull()
        val leftBottom = bottom?.leftOrNull()
        return setOfNotNull(
            left,
            leftTop,
            top,
            rightTop,
            right,
            rightBottom,
            bottom,
            leftBottom
        )
    }

    private fun leftOrNull(): Coordinate? {
        return runCatching { copy(x = x - 1) }.getOrNull()
    }

    private fun rightOrNull(): Coordinate? {
        return runCatching { copy(x = x + 1) }.getOrNull()
    }

    private fun topOrNull(): Coordinate? {
        return runCatching { copy(y = y - 1) }.getOrNull()
    }

    private fun bottomOrNull(): Coordinate? {
        return runCatching { copy(y = y + 1) }.getOrNull()
    }
}
