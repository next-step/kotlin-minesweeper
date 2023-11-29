package domain

class RandomPositionSelector(private val width: Int, private val height: Int) : PositionSelector {
    override fun selectPosition(): Position {
        val x = (0 until width).random()
        val y = (0 until height).random()

        return Position(x, y)
    }
}
