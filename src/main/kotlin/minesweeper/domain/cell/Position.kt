package minesweeper.domain.cell

data class Position(
    val x: Int,
    val y: Int
) {
    fun getAroundPositions() =
        listOf(left(), leftTop(), leftBottom(), right(), rightTop(), rightBottom(), top(), bottom())

    private fun left() = Position(x - 1, y)
    private fun leftTop() = Position(x - 1, y - 1)
    private fun leftBottom() = Position(x - 1, y + 1)
    private fun right() = Position(x + 1, y)
    private fun rightTop() = Position(x + 1, y - 1)
    private fun rightBottom() = Position(x + 1, y + 1)
    private fun top() = Position(x, y - 1)
    private fun bottom() = Position(x, y + 1)
}

fun Pair<Int, Int>.toPosition() = Position(first, second)
